package kr.bbaa.member.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.bbaa.board.entity.Board;
import kr.bbaa.member.domain.Role;
import kr.bbaa.reply.entity.Reply;
import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity(name = "tbl_member")
@Data
@ToString(exclude = {"boardList", "replyList"})
@Table(name = "tbl_member")
public class Member implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Member() {
	}
	
	@Id 
	@Column(name="MEMBER_ID")
	private String id;
	
	private String password;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private char enabled;
	
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
	private List<Board> boardList = new ArrayList<Board>();
	
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
	private List<Reply> replyList = new ArrayList<Reply>();
	
	
}//class
