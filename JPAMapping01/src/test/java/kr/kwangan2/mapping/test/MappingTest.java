package kr.kwangan2.mapping.test;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.kwangan2.mapping.entity.Professor;
import kr.kwangan2.mapping.entity.Student;
import kr.kwangan2.mapping.repository.ProfessorReopsitory;
import kr.kwangan2.mapping.repository.StudentReopsitory;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class MappingTest {

	@Autowired
	private StudentReopsitory stuRepo;
	@Autowired
	private ProfessorReopsitory proRepo;

//	@Test
	public void new_student() {
		Professor pro1 = new Professor("덤블도어");
		Professor pro2 = new Professor("맥고나걸");
		Professor pro3 = new Professor("스네이프");

		for (int i = 1; i <= 2; i++) {
			Student stu = new Student("Dio" + i);
			stu.setProfessor(pro1);
		}

		proRepo.save(pro1);

		for (int i = 1; i <= 2; i++) {
			Student stu = new Student("Jojo" + i);
			stu.setProfessor(pro2);
		}
		
		proRepo.save(pro2);
		
		for (int i = 1; i <= 2; i++) {
			Student stu = new Student("Paul" + i);
			stu.setProfessor(pro3);
		}
		
		proRepo.save(pro3);
	}
	
	//@Test
	public void 교수삭제() {
		proRepo.deleteById(3L);
	}
	
	//@Test
	public void	학생삭제() {
		stuRepo.deleteById(1L);
	}
	
	//@Test
	public void getStudentList() {
		List<Student> slist = (List<Student>) stuRepo.findAll();
		for(Student s : slist) {
			log.info(s.toString());
		}
	}

	//@Test
	public void getProfessorList() {
		List<Professor> plist = (List<Professor>) proRepo.findAll();
		for(Professor p : plist) {
			log.info(p.toString());
			
		}
	}
	
	//@Test
	public void updateStudent() {
	Student s = stuRepo.findById(1L).get();
		s.setStudentName("사실 해리");
		stuRepo.save(s);
	}
	//@Test
	public void updateProfessor() {
		Professor p = proRepo.findById(1L).get();
		p.setProfessorName("사실 푸치");
		proRepo.save(p);
	}
	

}// class
