package kr.kwangan2.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.jpa.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	
}
