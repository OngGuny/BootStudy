package kr.bbaa.board.security.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.bbaa.member.entity.Member;

public class SecurityUser extends User {
	private static final long serialVersionUID = 111L;
	private Member member;

	public SecurityUser(Member member) { //회원 객체 받아서
		super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;//멤버변수에 할당.  --> HTML 에서 사용하기 위함. 
	}

	public Member getMember() {
		return member;
	}
}
