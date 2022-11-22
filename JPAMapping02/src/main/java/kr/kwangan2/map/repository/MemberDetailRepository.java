package kr.kwangan2.map.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.map.domain.Board;
import kr.kwangan2.map.domain.MemberDetail;

public interface MemberDetailRepository extends
CrudRepository<MemberDetail, Long>,QuerydslPredicateExecutor<MemberDetail>{


}
