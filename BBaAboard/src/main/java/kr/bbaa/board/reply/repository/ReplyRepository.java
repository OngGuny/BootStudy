package kr.bbaa.board.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.bbaa.board.entity.Board;
import kr.bbaa.board.reply.entity.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Long>
	, QuerydslPredicateExecutor<Reply>{
	
	
	
}
