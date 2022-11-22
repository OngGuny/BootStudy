package kr.kwangan2.mapping.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Professor
 *
 */
@Entity
@Data
@Table(name="tbl_professors")
@ToString(exclude = "studentList")
@SequenceGenerator(
		name="professor_seq",
		sequenceName="tbl_professor_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class Professor implements Serializable {

	
	private static final long serialVersionUID = 2L;

	public Professor() {
		super();
	}
	public Professor(String proName) {
		this.professorName = proName;
	}
   
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "professor_seq")
	private Long pid;
	private String professorName;
	
	
	@OneToMany(mappedBy = "professor", fetch =FetchType.EAGER, cascade = CascadeType.ALL )
	List<Student> studentList = new ArrayList<Student>();
}
