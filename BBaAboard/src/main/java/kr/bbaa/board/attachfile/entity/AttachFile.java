package kr.bbaa.board.attachfile.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import kr.bbaa.board.entity.Board;
import lombok.Builder;
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
@SequenceGenerator(
		name = "attachfile_seq",
		sequenceName = "tbl_attachfile_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class AttachFile implements Serializable {

	private static final long serialVersionUID = 1L;

	public AttachFile() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "attachfile_seq")
	private Long fileId;

	private String orgNm;

	private String savedNm;

	private String savedPath; //얘만 넘겨주면 첨부됨 

	@ManyToOne
	@JoinColumn(name = "BOARD_SEQ")
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
		board.getAttachList().add(this);
	}

	@Builder
	   public AttachFile(Long fileId, String orgNm, String savedNm, String savedPath) {
	      this.fileId = fileId;
	      this.orgNm = orgNm;
	      this.savedNm = savedNm;
	      this.savedPath = savedPath;
	   }

}// class
