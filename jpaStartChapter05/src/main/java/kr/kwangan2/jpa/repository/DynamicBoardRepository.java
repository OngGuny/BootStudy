package kr.kwangan2.jpa.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.support.DefaultCrudMethods;

import kr.kwangan2.jpa.entity.Board;

public interface DynamicBoardRepository 
	extends CrudRepository<Board, Long> 
			, QuerydslPredicateExecutor<Board> {
	
	
}
