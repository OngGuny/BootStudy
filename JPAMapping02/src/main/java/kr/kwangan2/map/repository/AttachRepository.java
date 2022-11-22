package kr.kwangan2.map.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.map.domain.Attach;

public interface AttachRepository extends
CrudRepository<Attach, String>,QuerydslPredicateExecutor<Attach>{


}
