package kr.bbaa.board.reply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.bbaa.board.reply.entity.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Long>
	, QuerydslPredicateExecutor<Reply>{
	
	@Query("SELECT r FROM tbl_reply r")
	Page<Reply> getReplyList(Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("DELETE from tbl_reply r where r.board.seq = :boardseq")
	void deleteBoard(@Param("boardseq") Long seq);
	
}
