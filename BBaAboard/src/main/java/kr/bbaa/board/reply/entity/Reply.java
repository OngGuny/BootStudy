package kr.bbaa.board.reply.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.bbaa.board.entity.Board;
import kr.bbaa.member.entity.Member;
import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Reply
 *
 */
@Entity(name = "tbl_reply") //이름 안붙이면 엔티티 못찾는다고 오류뜸
@Data
@Table(name = "tbl_reply")
@ToString(exclude = {"board", "member"})
@SequenceGenerator(
		name = "reply_seq", 
		sequenceName = "tbl_reply_seq", 
		initialValue = 1, 
		allocationSize = 1
		)
public class Reply implements Serializable {

	private static final long serialVersionUID = 1L;

	public Reply() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq")
	private long rid;
	//c omment 가ㅣ 예약어라  안된거다 씹
	private String comments;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date regDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member; 
	
	public void setMember(Member member){
		this.member = member;
		member.getReplyList().add(this);
	}
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "BOARD_SEQ")
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
		board.getReplyList().add(this);
	}

}// class
