package kr.kwangan2.board.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity
@Data
@AllArgsConstructor
@Table(name="tbl_board")
@SequenceGenerator(
		name="board_seq"
		,sequenceName = "tbl_boardboot_seq"
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
	private Long seq;

	private String title;

	@Column(updatable = false)
	private String writer;

	private String content;

	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date createDate;

	@Column(insertable = false, updatable = true, columnDefinition = "number default 0")
	private Long cnt;
	
}
