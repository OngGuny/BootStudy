package kr.kwangan2.map.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.kwangan2.map.domain.Tag;

public interface TagRepository extends
CrudRepository<Tag, Long>,QuerydslPredicateExecutor<Tag>{


}
