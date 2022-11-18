package kr.kwangan2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kr.kwangan2.domain.Board;

public class JPAClient {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("jpatest");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx
        =em.getTransaction();
     
     try {
        
        tx.begin();

//			Board board = new Board(); // 스프링 쓰면 new 안쓰고 그냥 오토와이어드 함.
//			
//			board.setTitle("제목요");
//			board.setWriter("작성자");
//			board.setContent("내용");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//		    em.persist(board);
		    
//		    Board board = em.find(Board.class, 1L);
		    // 이렇게 찾아서 세팅만 해줘도 업데이트 된다. 
//			board.setTitle("검색한 게시글의 제목 수정");
			//걍 엔티티매니저보고 찾아온거 리무브 하라고 하면됨
			//em.remove(board);
			
			//select list
			String jpql = "select b from Board b order by b.seq desc ";
			//객체에 구문을 걸어서 검색하는거라 문구가 다르다. 
			
			List<Board> boardlist
				= em.createQuery(jpql,Board.class).getResultList();
			
			for(Board b : boardlist) {
				System.out.println("----->>>" +b.toString());
			}
			
			
	         tx.commit();
// 커밋도 안해주고 퍼시스트도 안해줬는데 뭘 자꾸 데이터를 찾냐..
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
	
	}//main
	
	
	
}//class
