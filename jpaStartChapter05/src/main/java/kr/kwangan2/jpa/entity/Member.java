package kr.kwangan2.jpa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity
@Data
@ToString(exclude = "boardList")
public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Member() {
	
	}

	public Member(String id, String password, String name, String role) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	//양방향 관계설정
	@OneToMany(mappedBy = "member",fetch=FetchType.EAGER,cascade = CascadeType.ALL)//멤버는 하나. 글은 여러개. 멤버가 쓴 글 다들고와야지.
	private List<Board> boardList = new ArrayList<Board>();
	
}//class
