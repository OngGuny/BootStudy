package kr.kwangan2.board.repository;

import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.board.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
