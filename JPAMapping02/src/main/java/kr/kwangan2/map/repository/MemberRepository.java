package kr.kwangan2.map.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.map.domain.Board;
import kr.kwangan2.map.domain.Member;

public interface MemberRepository extends
CrudRepository<Member, Long>,QuerydslPredicateExecutor<Member>{


}
