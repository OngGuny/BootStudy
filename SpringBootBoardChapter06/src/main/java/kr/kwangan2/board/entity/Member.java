package kr.kwangan2.board.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity implementation class for Entity: Member
 *
 */

@Entity
@Data
@Table(name="tbl_member")
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	public Member() {
		super();
	}

	@Id
	private String id;
	private String password;
	private String name;
	private String role;
}
