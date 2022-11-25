package kr.bbaa.board.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.bbaa.member.entity.Member;
import kr.bbaa.reply.entity.Reply;
import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity(name = "tbl_board")
@Data
@ToString(exclude = {"member" , "replyList"})
@Table(name = "tbl_board")
@SequenceGenerator(
		name = "board_seq",
		sequenceName = "tbl_board_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class Board implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Board() {
		super();
	}
   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "board_seq")
	@Column(name = "BOARD_SEQ")
	private Long seq; 
	
	private String classification;
	
	private String title;
	
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createDate = new Date();
	
	private Long cnt = 0L;
	
	private Long replyCnt = 0L;
	
	@ManyToOne //캐스케이드 되는게없노 ;ㅣ
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
	
	@OneToMany(mappedBy = "board" , fetch = FetchType.EAGER)
	private List<Reply> replyList = new ArrayList<Reply>();
	
}//class
