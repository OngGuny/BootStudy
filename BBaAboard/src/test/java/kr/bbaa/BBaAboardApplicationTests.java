package kr.bbaa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import kr.bbaa.board.entity.Board;
import kr.bbaa.board.repository.BoardRepository;
import kr.bbaa.member.domain.Role;
import kr.bbaa.member.entity.Member;
import kr.bbaa.member.repository.MemberRepository;
import kr.bbaa.reply.entity.Reply;
import kr.bbaa.reply.repository.ReplyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class BBaAboardApplicationTests {

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ReplyRepository replyRepo;
	
//	@Test 
	public void memberAndBoard() { //첫번쨰 오류. 엔티티의 이름과 리파지토리의 @query 구문의 테이블명이 같야아 맵핑이 된다.
		Member m = new Member();
		m.setId("Admin");
		m.setName("DIO");
		m.setPassword(encoder.encode("1"));
		m.setRole(Role.ROLE_ADMIN);
		m.setEnabled('y');
		memberRepo.save(m);
		
	}

	//@Test
	public void getBoard() {
		Board board = boardRepo.findById(2L).get();
		
		System.out.println(board.toString());
		
		Member mem = memberRepo.findById("Admin").get();
		System.out.println(mem.getBoardList().toString());
	}

	//@Test
	public void boardInsert() {
		Member m = new Member();
		m.setId("Admin");
		m.setName("DIO");
		m.setPassword(encoder.encode("1"));
		m.setRole(Role.ROLE_ADMIN);
		m.setEnabled('y');
		m.setReplyList(null);
		memberRepo.save(m);
		

		//Member m = memberRepo.findById("Admin").get();
		Board b = new Board();
		b.setMember(m); //일단 보드는 누가 썻는지. 작성자인 멤버가  필요하다. 
		b.setClassification("자유");
		b.setTitle("분류더한 제목");
		b.setContent("암튼 내용"); // 모든 캐스캐이드로 설정해놓으면 인서트나 업뎃할때 오류뜰 수 있으니까 삭제될떄만 같이 지워지도록 딜리트만 해놓자...
		b.setReplyList(null);
		boardRepo.save(b);
	}
	
	//@Test
	public void memberInsert() {
		Member m = new Member();
		m.setId("user");
		m.setPassword(encoder.encode("1"));
		m.setName("jojo");
		m.setRole(Role.ROLE_MEMBER);
		m.setEnabled('y');
		memberRepo.save(m);
	}
	
//	@Test
	public void replyInsert() {
//

//		List<Board> boardlist =  (List<Board>) boardRepo.findAll();		
//		Member m = memberRepo.findById("Admin").get();
		Member m =
				memberRepo.findById("Admin").get();
		Board b = 
		boardRepo.findById(1L).get();
		
		System.out.println(b);
//	System.out.println("///////////"+m);
		Reply r = new Reply();
		r.setBoard(b);
		r.setMember(m);
		r.setComments("영광의첫댓글");
//		
		replyRepo.save(r);
		
	}
	
	@Test
	public void replyTest() {
		Reply list = replyRepo.findById(1L).get();
		
		System.out.println( list );
	}
	
	
	
	
}//class
