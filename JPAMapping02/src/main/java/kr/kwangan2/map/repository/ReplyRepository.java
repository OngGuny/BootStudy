package kr.kwangan2.map.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.map.domain.Reply;

public interface ReplyRepository extends
CrudRepository<Reply, Long>,QuerydslPredicateExecutor<Reply>{


}
