package kr.kwangan2.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.kwangan2.board.entity.Member;
import kr.kwangan2.board.service.MemberService;

@SessionAttributes("member")
@Controller
@RequestMapping("/board")
public class LoginController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/login")
	public void loginView() {
	}

	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = memberService.getMember(member);

		if (findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);
			return "forward:/board/listBoard";
		} else {
			return "redirect:/board/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();// 세션 clear
		return "redirect:/index.html";	// 여기서 / 안붙여주면 그냥 클래스에 매핑된 /board 타고 들어가기때문에 / 붙여서 경로 초기화해줌
	}

}
