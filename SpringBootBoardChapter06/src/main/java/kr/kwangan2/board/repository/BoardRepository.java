package kr.kwangan2.board.repository;

import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.board.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

}
