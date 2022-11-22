package kr.kwangan2.map.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

import lombok.Data;

/**
 * Entity implementation class for Entity: Tag
 *
 */
@Entity
@Data
@Table(name="tbl_attachs2")
public class Attach implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Attach() {
	}
	
	@Id 
	private String fileName;
	private String uuid;
	private String filePath;
	//private boolean image;
	
	@ManyToOne
	@JoinColumn(name="bid",nullable = false)
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
		board.getAttachList().add(this);
	}
	
   
}
