package kr.kwangan2.mapping.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.mapping.entity.Student;

public interface StudentReopsitory extends
CrudRepository<Student, Long>,QuerydslPredicateExecutor<Student>{

}
