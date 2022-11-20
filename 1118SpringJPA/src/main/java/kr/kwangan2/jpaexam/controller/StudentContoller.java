package kr.kwangan2.jpaexam.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kwangan2.jpaexam.entity.Student;
import kr.kwangan2.jpaexam.repository.DynamicStudentRepository;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping(value="/")
@Log4j2
public class StudentContoller {
	
	@Autowired
	private DynamicStudentRepository dynamicStudentRepository;
	
	
	@RequestMapping("insertForm")
	public String 삽입폼() {
		
		return "insertForm";
	}
	
	@RequestMapping("updateForm")
	public String 수정폼(Model model,Long sid) {
		
		Student student = dynamicStudentRepository.findById(sid).get();
		
		
		model.addAttribute(student);
		return "insertForm";
	}
	
	@RequestMapping("insert")
	public String 삽입(Student student) {
		log.info(student);
		student.setRegDate(new Date());
		dynamicStudentRepository.save(student);
		return "redirect:/list";
	}
	
	//model.addAttribute("student", dynamicStudentRepository.save(student));
	
	@RequestMapping("list")
	public String 메인화면(Model model) {
		Pageable pq = PageRequest.of(0, 100, Sort.Direction.DESC, "sid");
		List<Student> list = (List<Student>) dynamicStudentRepository.findAll(Sort.by(Sort.Direction.DESC,"sid"));
		model.addAttribute(list);
		return "list";
	}
	
	//@RequestMapping("list")
	public String 메인() {
		return "list";
	}
	
	
	
	@RequestMapping("update")
	public String 수정(Student student) {
		Student student기존 = dynamicStudentRepository.findById(student.getSid()).get();
		student기존.setName(student.getName());
		student기존.setAddr(student.getAddr());
		student기존.setAge(student.getAge());
		student기존.setRegDate(new Date());
		dynamicStudentRepository.save(student기존);
		return "redirect:/list";
	}
	
	
	@RequestMapping("delete")
	public String 삭제(Long sid) {
		dynamicStudentRepository.deleteById(sid);
		
		return "redirect:/list";
	}
	
	
	
}//class
