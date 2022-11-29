package kr.bbaa.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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

	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "board/insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		if (board.getMember().getName() != principal.getUsername()) {
			log.info("WrongWriter....");
			return "redirect:getBoard";
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


		model.addAttribute("board", b);
		model.addAttribute("visitor", principal.getMember());
		model.addAttribute("replyList", replyList);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("searchResult", search);
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

	@PostMapping("/updateReplyProc") // rid, 수정된 contents 받음 
	public String updateReplyProc(Reply reply) {
		System.out.println("어디있니얘야.."+boardService.getReply(reply));
		Long seq = boardService.getReply(reply).getBoard().getSeq();
		boardService.updateReply(reply);

		return "redirect:getBoard?seq="+seq;
	}

}// class
