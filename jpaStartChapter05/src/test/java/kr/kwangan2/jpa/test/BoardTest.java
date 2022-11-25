package kr.kwangan2.jpa.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;

import kr.kwangan2.jpa.entity.Board;
import kr.kwangan2.jpa.entity.Member;
import kr.kwangan2.jpa.entity.QBoard;
import kr.kwangan2.jpa.repository.BoardRepository;
import kr.kwangan2.jpa.repository.DynamicBoardRepository;
import kr.kwangan2.jpa.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class BoardTest {
	@Autowired
	private BoardRepository boardRepo;

	@Autowired
	private DynamicBoardRepository dynamicBoardRepo;

	@Autowired
	private MemberRepository memberRepo;

	// @Test
	public void dataPrepare() {
		for (int i = 1; i <= 200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			// board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
	}
	// @Test
//	public void testInsertBoard() {
//		Board board = new Board("제목"/*,"작성자"*/,"내용",new Date(),0L); 
//		boardRepo.save(board);
//	}

	// @Test
	public void testGetBoard() {
		Board board = boardRepo.findById(34L).get();
		log.info(board);
	}

	// @Test
	public void testFindByTitleContaining() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");

		Page<Board> pageInfo = boardRepo.findByTitleContaining("제목", paging);

		System.out.println("PAGE SIZE : " + pageInfo.getSize());
		System.out.println("TOTAL PAGES : " + pageInfo.getTotalPages());
		System.out.println("TOTAL COUNT : " + pageInfo.getTotalElements());
		System.out.println("NEXT : " + pageInfo.nextPageable());

		List<Board> boardList = pageInfo.getContent();

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

//	@Test
//	public void testFindByTitleContaining() {
//		Pageable paging = PageRequest.of(0, 5);
//		
//		List<Board> boardList = boardRepo.findByTitleContaining("제목", paging);
//		
//		System.out.println("검색 결과");
//		for (Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}

//	@Test
//	public void testFindByTitleContainingOrderBySeqDesc() {
//		List<Board> boardList = 
//			boardRepo.findByTitleContainingOrderBySeqDesc("17");
//		
//		System.out.println("검색 결과");
//		for (Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}

//	@Test
//	public void testFindByTitleContainingOrContentContaining() {
//		List<Board> boardList = 
//			boardRepo.findByTitleContainingOrContentContaining("17", "17");
//		
//		System.out.println("검색 결과");
//		for (Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}

//	@Test
//	public void testByContentContaining() {
//		List<Board> boardList = boardRepo.findByContentContaining("17");
//		
//		System.out.println("검색 결과");
//		for (Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}

	// @Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("테스트 제목 10");

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

	// @Test
	public void testQueryAnnotationTest1() {
		List<Board> boardList = boardRepo.queryAnnotationTest1("제목 10");
		for (Board b : boardList) {
			log.info("1------>" + b);
		}
	}

	// @Test
	public void testQueryAnnotationTest2() {
		List<Board> boardList = boardRepo.queryAnnotationTest2("제목 10");
		for (Board b : boardList) {
			log.info("2---->>" + b);
		}
	}

	// @Test
	public void testQueryAnnotationTest3() {
		List<Object[]> boardList2 = boardRepo.queryAnnotationTest3("제목 10");
		for (Object[] b : boardList2) {
			log.info("3---->>" + Arrays.toString(b));
		}
	}

//	@Test
	public void testQueryAnnotationTest4() {
		List<Object[]> boardList3 = boardRepo.queryAnnotationTest4("제목 10");
		for (Object[] b : boardList3) {
			log.info("4---->>" + Arrays.toString(b));
		}
	}

	// @Test
	public void testQueryAnnotationTest5() {
		Pageable page = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		List<Board> boardList3 = boardRepo.queryAnnotationTest5(page);
		for (Board b : boardList3) {
			log.info("5---->>" + b);
		}
	}

	/*
	 * @Test public void testDynamicQuery() { String searchCondition = "TITLE";
	 * String searchKeyword = "제목 10";
	 * 
	 * BooleanBuilder builder = new BooleanBuilder(); QBoard qboard = QBoard.board;
	 * 
	 * if(searchCondition.equals("TITLE")) {
	 * builder.and(qboard.title.like("%"+searchKeyword+"%")); } else
	 * if(searchCondition.equals("context")) {
	 * builder.and(qboard.content.like("%"+searchKeyword+"%")); }
	 * 
	 * Pageable paging = PageRequest.of(0, 5); Page<Board> boardList3 =
	 * dynamicBoardRepo.findAll(builder, paging);
	 * 
	 * for(Board b : boardList3) { log.info("Dynamic Query---->>"+b); }
	 * 
	 * }
	 */

	@Test // 밑에서는 리포지토리를 ㅋ인터페이스로 안하고 클래스로 해서 뽀사졌던거.
	public void testManyToOneInsert() {
		
		
		Member member1 = new Member("member1", "member1Pass", "Dio", "The world");


		for (int i = 1; i <= 3; i++) {
			Board board1 = new Board("제목" + i, "내용+ 연관관계 설정후" + i, new Date(), 0L);
			board1.setMember(member1);
			// boardRepo.save(board);
		}
		// for

		memberRepo.save(member1);
		
		Member member2 = new Member("member2", "member2Pass", "JoJo", "StarPlatina");
		
		for (int i = 1; i <= 3; i++) {
			Board board2 = new Board("제목" + i, "내용+ 연관 설정 후" + i, new Date(), 0L);
			// boardRepo.save(board);
			board2.setMember(member2); 
		}
		// for

		memberRepo.save(member2);
		/*
		 * 이렇게 바꿀 수 있는 이유가 양방향 매핑이 되었기 때문에 잘 찾아간다는것 .
		 * 
		 */

	}// manyTOone

	// @Test
	public void testMane() {
		Board board = dynamicBoardRepo.findById(5L).get();
		log.info(board);
	}

	// @Test
	public void 양방향매핑() {
		Member member = memberRepo.findById("member1").get();
		List<Board> list = member.getBoardList();
		for (Board board : list) {
			log.info(board.toString());
		}
	}

	//@Test
	public void 삭제() {
		memberRepo.deleteById("member2");
	}
	
	
}
// class
