package kr.kwangan2.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity
@Data
@Table//(name="tbl_boards") 이건 오라클용
public class Board implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue//얘도 오라클용(strategy = GenerationType.AUTO)
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
	
	
	public Board() {
	}

	public Board(String title, String writer, String content, Date createDate, Long cnt) {
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.createDate = createDate;
		this.cnt = cnt;
	}
	
}//class
