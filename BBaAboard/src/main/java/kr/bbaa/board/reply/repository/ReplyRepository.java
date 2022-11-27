package kr.bbaa.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.bbaa.board.entity.Board;
import kr.bbaa.reply.entity.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Long>
	, QuerydslPredicateExecutor<Reply>{
	
	
	
}
