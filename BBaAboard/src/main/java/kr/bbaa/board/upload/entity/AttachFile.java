package kr.bbaa.board.upload.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kr.bbaa.board.entity.Board;
import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: AttachFile
 *
 */
@Entity(name = "tbl_attachfile")
@Data
@Table(name = "tbl_attachfile")
@ToString(exclude = "board")
public class AttachFile implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	public AttachFile() {
		super();
	}
   
	@Id
	private String fileName;
	private String uploadPath;
	private String uuid;
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "BOARD_SEQ")
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
		board.getAttachList().add(this);
	}
	
}//class
