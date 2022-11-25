package kr.kwangan2.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity
@Data
@Table//(name="tbl_boards") 이건 오라클용
@ToString(exclude = "member")
public class Board implements Serializable { // 얘가 member를 가지는애. b has a member 주인.

	
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue//얘도 오라클용(strategy = GenerationType.AUTO)
	private Long seq;
	private String title;
	//private String writer;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
	
	
	public Board() {
	}

	public Board(String title/*, String writer*/, String content,
			Date createDate, Long cnt, Member member) {
		this.title = title;
		//this.writer = writer;
		this.content = content;
		this.createDate = createDate;
		this.cnt = cnt; 
		this.member = member;
	}
	
	public Board(String title, String content, Date createDate, Long cnt) {
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.cnt = cnt;
	}

	@ManyToOne
	@JoinColumn(name="MEMBER_ID",nullable = false) //다른 설정 안하면 left outter 조인 이러면 근데 성능 딸려서 이너조인 해줘야한다.
	private Member member;
	
	public void setMember(Member member) {
		this.member=member; 
		member.getBoardList().add(this); //이걸 해줘야 맵핑 되서 이너조인으로 가져올 수 있게 된다.
										// 하나 추가되면서 멤버의 보드리스트에 추가된 보드가 add되는것. 
	}
	
}//class
