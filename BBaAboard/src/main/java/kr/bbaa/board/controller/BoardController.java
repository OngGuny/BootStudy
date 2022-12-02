package kr.bbaa.board.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import kr.bbaa.board.attachfile.entity.AttachFile;
import kr.bbaa.board.attachfile.service.AttachFileService;
import kr.bbaa.board.domain.Search;
import kr.bbaa.board.entity.Board;
import kr.bbaa.board.reply.entity.Reply;
import kr.bbaa.board.security.domain.SecurityUser;
import kr.bbaa.board.service.BoardService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board/")
@Log4j2
@SessionAttributes("principal")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private AttachFileService attachFileService;
	

	@GetMapping("/insertBoard")
	public String insertBoardView() {
		
		return "board/insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal
			,@RequestParam("files") List<MultipartFile> files
			) throws Exception{
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		
		for (MultipartFile multipartFile : files) {
	         board.setFileId(attachFileService.saveFile(multipartFile, board));
	      }
		
		return "redirect:getBoardList";
	}

	@PostMapping("/updateBoard")// 글 내용 보면서 수정가능. 근데 작성자가 아니면 수정불가.
	public String updateBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		Board upb = boardService.getBoard(board);
		
		if (!upb.getMember().getName().equals(principal.getMember().getName())) {
			log.info("WrongWriter....");
			return "redirect:getBoardList";
		}
		
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}

	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, Search search) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		if (search.getClassification ()== null)
			search.setClassification("");
		int currntPage = search.getPage();
		Page<Board> boardList = boardService.getBoardList(search ,currntPage);//이렇게 받아줘야 검색후 페이징 해도 검색조건이 유지됨
		//첫번쨰 페이지일때 1만 뜨게 해주는 코드
		if(boardList.getNumberOfElements()==0) {
			search.setPage(1);
		}else {
			search.setPage(boardList.getTotalPages());
		}
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("currentPage", currntPage);
		model.addAttribute("searchResult", search);
		return "board/getBoardList";
	}

	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model, Search search, @AuthenticationPrincipal SecurityUser principal) {
		Board b = boardService.getBoard(board);
		b.setCnt(b.getCnt() + 1);
		boardService.updateBoard(b);// 조회수
		
		int replyPage = search.getReplyPage();
		
		Page<Reply> replyList = boardService.getReplyList(b, search, replyPage);
		
		//첫번쨰 페이지일때 1만 뜨게 해주는 코드
		
		if(replyList.getNumberOfElements()==0) {
			search.setReplyPage(1);
		}else {
			search.setReplyPage(replyList.getTotalPages());
		}
		
		//첨부파일
		List<AttachFile> files = attachFileService.fileAllView(board.getSeq());

		
		model.addAttribute("board", b);
		model.addAttribute("visitor", principal.getMember());
		model.addAttribute("replyList", replyList);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("searchResult", search);
	    model.addAttribute("fileList", files);

		return "board/getBoard";
	}

	@PostMapping("/insertReply") // board.seq 만 던짐
	public String insertReply(Board board, Reply reply, @AuthenticationPrincipal SecurityUser principal) {
		reply.setBoard(board);
		reply.setMember(principal.getMember());
		boardService.insertReply(reply);

		boardService.updateBoardReplyCnt(board);// 댓글 쓸때 한번만 업데이트하도록함.

		return "redirect:getBoard?seq=" + board.getSeq();
	}

	@RequestMapping("/deleteReply")
	public String deleteReply(Reply reply) {
		Long seq = boardService.getReply(reply).getBoard().getSeq();
		boardService.deleteReply(reply);

		return "redirect:getBoard?seq=" + seq;
	}
	
	
	@RequestMapping("/updateReply")			//rid 하나만 가짐
	public String updateReply(Model model,Reply reply) {
		Reply rep = boardService.getReply(reply);
		model.addAttribute("reply", rep);
		
		return "board/updateReply";
	}

	@PostMapping("/updateReplyProc") // rid, 수정된 comments 받음 
	public String updateReplyProc(Reply reply) {
		System.out.println("어디있니얘야.."+boardService.getReply(reply));
		Long seq = boardService.getReply(reply).getBoard().getSeq();
		boardService.updateReply(reply);

		return "redirect:getBoard?seq="+seq;
	}
	
	
	//첨부파일 보여주기. 
	@GetMapping("/images/{fileId}")
	   @ResponseBody
	   public Resource imageView(@PathVariable("fileId") Long fileid, Model model) throws IOException {

	      AttachFile file = attachFileService.downloadImage(fileid);
	      String savePath = file.getSavedPath();
	      System.out.println("================>>> file path" + file.getSavedPath());
	      System.out.println("================>>> file path"
	            + file.getSavedPath().substring(file.getSavedPath().length() - 3, file.getSavedPath().length()));
	      if (savePath.substring(savePath.length() - 3, savePath.length()).equals("png")
	            || savePath.substring(savePath.length() - 3, savePath.length()).equals("jpg")) {
	         UrlResource url = new UrlResource("file", savePath);
	         System.out.println("===============>notnullll");
	         model.addAttribute("test", "exe");
	         return url;
	      } else {
	         System.out.println("===============>nulllll");
	         model.addAttribute("test", "not");
	      }
	      return null;
	   }
	
	//첨부파일 다운로드 
	@GetMapping("/attach/{id}")
	   public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {

	      AttachFile file = attachFileService.downloadImage(id);

	      UrlResource resource = new UrlResource("file:" + file.getSavedPath());

	      String encodedFileName = UriUtils.encode(file.getOrgNm(), StandardCharsets.UTF_8);

	      // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
	      // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
	      String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

	      return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
	   }
	
	@GetMapping("/images/deleteFile")
	   public String deleteFile(AttachFile attachFiles, Board board) throws IOException {

	      
	       attachFileService.deleteFile(attachFiles.getFileId());
	      
	      return "redirect:/board/getBoard?seq="+board.getSeq();
	   }
	   
	   
	
}// class
