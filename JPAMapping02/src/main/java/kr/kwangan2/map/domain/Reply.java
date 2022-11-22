package kr.kwangan2.map.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Tag
 *
 */
@Entity
@Data
@Table(name="tbl_replys2")
@ToString(exclude = "board")
@SequenceGenerator(
		name="reply_seq",
		sequenceName = "tbl_replys2_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class Reply implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Reply() {
		super();
	}
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reply_seq")
	private long rid;
	private String contents;
   
	@ManyToOne
	@JoinColumn(name="bid",nullable = false)
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
		board.getReplyList().add(this);
	}
	
}//class
