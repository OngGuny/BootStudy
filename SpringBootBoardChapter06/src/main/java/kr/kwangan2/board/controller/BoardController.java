package kr.kwangan2.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.kwangan2.board.entity.Board;
import kr.kwangan2.board.entity.Member;
import kr.kwangan2.board.service.BoardService;

@SessionAttributes("member")
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	//예시로 만들어 놓은거 
	//@RequestMapping("/boardInsert")
	public String 연습(Model model) {
		List<Board> boardList = new ArrayList<Board>();
//		for(int i = 1; i<=10;i++) {
//			Board b = 
//					new Board(
//							new Long(i),
//							"제목"+i,
//							"작성자"+i,
//							"내용"+i,
//							new Date(),
//							0L
//							);
//			boardList.add(b);
//		}//for
		model.addAttribute("boardList",boardList);
		return "/board/listBoard";
		//컴파일이 안되도 경로 오류가 뜬다. 그래서 파일 있는데도 못찾아가는거. 
		//컴파일 이슈는 pom 의 디펜던시 버젼 문제 
	}
	
	@RequestMapping("/listBoard") //얘는 나머지 애들이 포워드로 여기로 왔을때, 포스트든 겟이든 다 받아 주려고 request한거고
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		List<Board> boardList = boardService.listBoard(board);

		model.addAttribute("boardList", boardList);
		return "/board/listBoard";
	}

	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		return "board/insertBoard";
	}

	@PostMapping("/insertBoardProc")
	public String insertBoardProc(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.insertBoard(board);
		return "redirect:/board/listBoard";
	}

	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		Board b = boardService.getBoard(board);
		b.setCnt(b.getCnt()+1);
		boardService.updateBoardProc(b);
		model.addAttribute("board", b);
		return "/board/getBoard";
	}

	@PostMapping("/updateBoardProc")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.updateBoardProc(board);
		return "forward:/board/listBoard";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.deleteBoard(board);
		return "forward:/board/listBoard";
	}

	@RequestMapping("/hello")
	public String 안녕() {
		return "/board/hello";
	}
}