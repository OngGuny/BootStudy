package kr.kwangan2.jpaexam.test;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;

import kr.kwangan2.jpaexam.entity.QStudent;
import kr.kwangan2.jpaexam.entity.Student;
import kr.kwangan2.jpaexam.repository.DynamicStudentRepository;
import kr.kwangan2.jpaexam.repository.StudentRepository;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class StudentTest {//테스트 메소드 하나라도 있으면 메이븐 인스톨 안됨 

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DynamicStudentRepository dynamicStudentRepository;

	
//	@Test
	public void testInsert() {
		Student student = new Student("Dio", 110, "England");
		dynamicStudentRepository.save(student);
	}
	
	//@Test
	public void testFind() {
	log.info("어디잇니~~~~~~~~~~~~~"+	dynamicStudentRepository.findById(1L).get());
	}
	
	//@Test
	public void testUpdate() {
		
	Student student =  dynamicStudentRepository.findById(1L).get();
		student.setName("JoJO");
		
		dynamicStudentRepository.save(student);
	}
	
	//@Test
	public void testDelete() {
		dynamicStudentRepository.deleteById(202L);
	}
	
	//@Test
	public void inserts() {
		for (int i = 1; i <= 200; i++) {
			Student board = new Student();
			board.setName("무다무다무다무다무다"+i);
			board.setAge(i);
			board.setAddr("Englands");			
			board.setRegDate(new Date());	
			dynamicStudentRepository.save(board);
		}
	}
	
	
	//@Test
	public void testPaging() {
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "sid");
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		//QStudent qStudent= QStudent.student;
		//위에 q스튜던트는 검색할 떄, 
		Page<Student> studentList = dynamicStudentRepository.findAll(booleanBuilder,paging);
		
		for (Student s : studentList) {
			System.out.println("---> " + s.toString());
		}
		
	}//paging
	
	
}//class
