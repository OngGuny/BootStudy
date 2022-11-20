package kr.kwangan2.jpaexam.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity implementation class for Entity: Student
 *
 */
@Entity
@Data
@Table(name="tbl_student")
@AllArgsConstructor
public class Student implements Serializable {

	@Id @GeneratedValue(generator = "SID_STUDENT_SEQUENCE",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SID_STUDENT_SEQUENCE", sequenceName = "STUDENT_SEQUENCE",initialValue = 1,allocationSize = 1)
	private Long sid;
	private String name;
	private int age;
	private String addr;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date regDate;
	
	
	private static final long serialVersionUID = 1L;

	public Student() {
	}

	public Student(String name, int age, String addr) {
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
   
	
	
	
}
