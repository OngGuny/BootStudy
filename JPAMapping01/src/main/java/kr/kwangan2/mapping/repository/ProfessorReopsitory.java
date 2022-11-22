package kr.kwangan2.mapping.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.mapping.entity.Professor;

public interface ProfessorReopsitory extends
CrudRepository<Professor, Long>,QuerydslPredicateExecutor<Professor>{

}
