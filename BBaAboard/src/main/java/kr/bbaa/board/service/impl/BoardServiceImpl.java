package kr.bbaa.board.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import kr.bbaa.board.attachfile.repository.AttachFileRepository;
import kr.bbaa.board.domain.Search;
import kr.bbaa.board.entity.Board;
import kr.bbaa.board.entity.QBoard;
import kr.bbaa.board.reply.entity.QReply;
import kr.bbaa.board.reply.entity.Reply;
import kr.bbaa.board.reply.repository.ReplyRepository;
import kr.bbaa.board.repository.BoardRepository;
import kr.bbaa.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService { // 아니 서비스 구현해야지 ㅋㅋㅋㅋ 안하니까 정성스럽게 노 빈 에러 뜸 ;

	@Autowired
	private BoardRepository boardRepo;

	@Autowired
	private ReplyRepository replyRepo;
	
	@Autowired
	private AttachFileRepository attachFileRepo;
	
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		System.out.println("업데이트 되나요?");
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		System.out.println(findBoard);
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	@Override
	public void deleteBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		System.out.println("여기엔 뭐가?" + b.getReplyList());

//		if (!b.getReplyList().isEmpty()) {
//			for (Reply rep : b.getReplyList()) {
//				replyRepo.deleteById(rep.getRid());
//			}
//		}
		
		replyRepo.deleteBoard(b.getSeq());
		attachFileRepo.deleteBoard(b.getSeq());
		boardRepo.deleteById(b.getSeq());
	}

	@Override
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}

	@Override
	public Page<Board> getBoardList(Search search, int page) {
		BooleanBuilder builder = new BooleanBuilder();
		System.out.println("카테고리어디?"+search.getClassification());
		QBoard qBoard = QBoard.board;

		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qBoard.title.like("%" + search.getSearchKeyword() + "%"));
			builder.and(qBoard.classification.like("%" + search.getClassification() + "%")); // 보드, html에 classfication
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qBoard.content.like("%" + search.getSearchKeyword() + "%"));
			builder.and(qBoard.classification.like("%" + search.getClassification() + "%")); // 보드, html에 classfication
		}

		Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "seq");// 내림차순, 10개씩만 , 1페이지 부터

		return boardRepo.findAll(builder, pageable);
	}

	@Override
	public Page<Reply> getReplyList(Board board, Search search, int page) {
		Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "rid");// 내림차순, 10개씩만 , 1페이지 부터
		BooleanBuilder builder = new BooleanBuilder();
		QReply qReply = QReply.reply;
		builder.and(qReply.board.seq.eq(board.getSeq()));

		return replyRepo.findAll(builder, pageable);
	}

	@Override
	public void insertReply(Reply reply) {
		// boardRepo.findById(null)
		replyRepo.save(reply);
	}

	@Override
	public void deleteReply(Reply reply) {
		replyRepo.deleteById(reply.getRid());

	}

	@Override
	public void updateReply(Reply reply) {
		Reply r = replyRepo.findById(reply.getRid()).get();
		r.setComments(reply.getComments());
		r.setRegDate(new Date());
		replyRepo.save(r);
	}

	@Override
	public Reply getReply(Reply reply) {

		return replyRepo.findById(reply.getRid()).get();

	}

	@Override
	public void updateBoardReplyCnt(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		Long replyCnt = (long) findBoard.getReplyList().size();

		findBoard.setReplyCnt(replyCnt);

		boardRepo.save(findBoard);
	}

	@Override
	public List<Board> getExcelBoardList() {
		return (List<Board>) boardRepo.findAll();
	}

}// class
