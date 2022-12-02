package kr.bbaa.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.bbaa.board.domain.Search;
import kr.bbaa.board.entity.Board;
import kr.bbaa.board.service.BoardService;
import kr.bbaa.mail.domain.MailDTO;
import kr.bbaa.mail.service.MailService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MailController {
   
   @Autowired
   private BoardService boardService;
   
   private final MailService mailService;
   
   @GetMapping("/mail")
   public String dispMail() {
      return "mail";
   }
   
   @PostMapping("/mail")
   public String execMail(MailDTO mailDTO, Model model, Search search) {
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
		
		if(mailDTO.getAttachFileList()!=null) { //첨부파일이 있을 때
			System.out.println("첨부파일 있는 메일 발송");
			mailService.sendAttachMail(mailDTO);
		}else {
			System.out.println("첨부파일 없는 메일 발송");
      mailService.sendSimpleMail(mailDTO);
		}
		
      return "redirect:/board/getBoardList";
   }

}