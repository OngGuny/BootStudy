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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name="tbl_members2")
@ToString(exclude = {"tagList","boardList","memberdetail"})
@SequenceGenerator(
		name="member_seq"
		,sequenceName = "tbl_members2_seq"
		,initialValue = 1
		,allocationSize = 1
		)
public class Member implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Member() {
		super();
	}
   
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_seq")
	private long mid;
	private String mName;
	private String mPass;
	
	@OneToMany(mappedBy = "member",fetch =FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();
	
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Tag> tagList = new ArrayList<Tag>();
	
	@OneToOne 
	private MemberDetail memberDetail;

}//class
