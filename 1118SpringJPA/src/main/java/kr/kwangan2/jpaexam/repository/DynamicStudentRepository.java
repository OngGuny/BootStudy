package kr.kwangan2.jpaexam.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import kr.kwangan2.jpaexam.entity.Student;

public interface DynamicStudentRepository 
	extends CrudRepository<Student, Long>
			,QuerydslPredicateExecutor<Student>,PagingAndSortingRepository<Student, Long> {
	

}
