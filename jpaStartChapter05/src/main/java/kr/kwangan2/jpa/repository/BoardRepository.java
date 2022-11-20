package kr.kwangan2.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kr.kwangan2.jpa.entity.Board;

public interface BoardRepository extends CrudRepository<Board,Long> {
	
	List<Board> findByTitle(String title);
	Page<Board> findByTitleContaining(String title, Pageable page);
	
	@Query("select b from Board b where b.title like %?1% order by b.seq desc ")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("select b from Board b where b.title like %:searchKeyword% order by b.seq desc ")
	List<Board> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	//   :searchKeyword   네임드 파라미터  앞에 콜론 주의
	
	@Query(" select b.seq, b.title, b.writer, b.createDate"
			+ " from Board b"
			+ " where b.title like %?1%"
			+ " order by b.seq desc "
			)
	List<Object[]> queryAnnotationTest3(String searchKeyword);
		
	
	@Query(
			value="select seq, title, writer, create_date"
					+ " from board where title like '%'||?1||'%'"
					+ " order by seq desc",// 얘는 sql 의 쿼리 위에는 전부 jpql
			nativeQuery = true		
	)//네이티브 쓰는건 위에 변수같은 거  <? 등> 설정해줄때 다를 수 있어서 설정해주는거 
	List<Object[]> queryAnnotationTest4(String searchKeyword);
	
	@Query(" select b from Board b order by b.seq desc")
	List<Board> queryAnnotationTest5(Pageable paging);
	
	
	
}//class
