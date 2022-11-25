package kr.bbaa.member.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.bbaa.member.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String>
	, QuerydslPredicateExecutor<Member>{

}
