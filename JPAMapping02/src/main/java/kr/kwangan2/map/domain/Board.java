package kr.kwangan2.map.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Tag
 *
 */
@Entity
@Data
@Table(name="tbl_boards2")
@ToString(exclude = {"tagList","attachList","replyList","member"})
@SequenceGenerator(
		name="board_seq"
		,sequenceName = "tbl_boards2_seq"
		,initialValue = 1
		,allocationSize = 1
		)
public class Board implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Board() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "board_seq")
	private Long bid;
	private String title;
	private String content;
	
	@OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
	private List<Tag> tagList = new ArrayList<Tag>();

	@OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
	private List<Attach> attachList = new ArrayList<Attach>();
	
	@OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
	private List<Reply> replyList = new ArrayList<Reply>();

	@ManyToOne
	@JoinColumn(name="mid",nullable= false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
	
	
}//class
