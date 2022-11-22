package kr.kwangan2.mapping.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

/**
 * Entity implementation class for Entity: Professor
 *
 */
@Entity
@Data
@Table(name="tbl_students")
@ToString(exclude = "professor")
@SequenceGenerator(
		name="student_seq",
		sequenceName="tbl_student_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class Student implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Student() {
	}
	public Student(String studentName) {
		this.studentName = studentName;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_seq")
	private long sid;
	private String studentName;
	
	@ManyToOne
	@JoinColumn(name="pid",nullable = false)
	private Professor professor;
	
	public void setProfessor(Professor pro) {
		this.professor = pro;
		pro.getStudentList().add(this);
	}
	
	
}
