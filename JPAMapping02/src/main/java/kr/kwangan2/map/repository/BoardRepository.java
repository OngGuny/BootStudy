package kr.kwangan2.map.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.map.domain.Board;

public interface BoardRepository extends
CrudRepository<Board, Long>,QuerydslPredicateExecutor<Board>{


}
