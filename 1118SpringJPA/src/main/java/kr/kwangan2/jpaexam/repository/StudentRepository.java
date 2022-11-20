package kr.kwangan2.jpaexam.repository;

import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.jpaexam.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	
	
}
