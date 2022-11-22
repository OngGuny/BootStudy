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
@ToString(exclude = "mid")
@Table(name="tbl_memberdetails2")
public class MemberDetail implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public MemberDetail() {
		super();
	}
   
	@Id
	private long mid;
	
	private String name;
	private int age;
	private String addr;
	
	
}//class
