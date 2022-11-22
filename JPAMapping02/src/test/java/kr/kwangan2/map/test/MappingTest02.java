package kr.kwangan2.map.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.kwangan2.map.domain.Member;
import kr.kwangan2.map.domain.MemberDetail;
import kr.kwangan2.map.domain.Tag;
import kr.kwangan2.map.repository.AttachRepository;
import kr.kwangan2.map.repository.BoardRepository;
import kr.kwangan2.map.repository.MemberDetailRepository;
import kr.kwangan2.map.repository.MemberRepository;
import kr.kwangan2.map.repository.ReplyRepository;
import kr.kwangan2.map.repository.TagRepository;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class MappingTest02 {
	
	@Autowired
	private AttachRepository attRepo;
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private MemberDetailRepository mDetailRepo;
	@Autowired
	private ReplyRepository replyRepo;
	@Autowired
	private TagRepository tagRepo;
	
	@Test
	public void 멤버삽입() {
		Member mem = new Member();
		mem.setMName("1번 타자~");
		mem.setMPass("비밀번호");
		memberRepo.save(mem);
	}
	
	
//	@Test
//	public void apaqj() {
//		Tag tag = new Tag();
//		tag.set
//		attRepo.save(null)
//	}
	
	
	
}//class
