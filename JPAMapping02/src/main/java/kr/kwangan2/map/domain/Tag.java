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
@Table(name="tbl_tags2")
@ToString(exclude = "board")
@SequenceGenerator(
		name="tag_seq",
		sequenceName = "tbl_tags2_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class Tag implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Tag() {
		super();
	}
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tag_seq")
	private long tid;
	private String tagName;
	
	@ManyToOne
	@JoinColumn(name="mid",nullable = false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getTagList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name="bid",nullable = false)
	private Board board;
	public void setBoard(Board board) {
		this.board = board;
		board.getTagList().add(this);
	}
	
}//class
