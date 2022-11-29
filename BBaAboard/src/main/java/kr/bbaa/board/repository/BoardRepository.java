package kr.bbaa.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.bbaa.board.entity.Board;
import kr.bbaa.board.reply.entity.Reply;

public interface BoardRepository extends CrudRepository<Board, Long>
	, QuerydslPredicateExecutor<Board>{

	@Query("SELECT b FROM tbl_board b")
	Page<Board> getBoardList(Pageable pageable);
	
	
}
