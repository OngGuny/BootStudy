package kr.bbaa.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import kr.bbaa.board.domain.Search;
import kr.bbaa.board.entity.Board;
import kr.bbaa.board.entity.QBoard;
import kr.bbaa.board.repository.BoardRepository;
import kr.bbaa.board.service.BoardService;
import kr.bbaa.reply.entity.Reply;
import kr.bbaa.reply.repository.ReplyRepository;

@Service
public class BoardServiceImpl implements BoardService { // 아니 서비스 구현해야지 ㅋㅋㅋㅋ 안하니까 정성스럽게 노 빈 에러 뜸 ;

	@Autowired
	private BoardRepository boardRepo;

	@Autowired
	private ReplyRepository replyRepo;

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();

		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}

	@Override
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}

	@Override
	public Page<Board> getBoardList(Search search) {
		BooleanBuilder builder = new BooleanBuilder();

		QBoard qboard = QBoard.board;

		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qboard.content.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("CLASSIFICATION")) {
			builder.and(qboard.classification.like("%" + search.getSearchKeyword() + "%")); // 보드, html에 classfication
																							// 추가함
		}

		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");// 내림차순, 10개씩만 , 1페이지 부터

		return boardRepo.findAll(builder, pageable);
	}

	@Override
	public void insertReply(Reply reply) {
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
		replyRepo.save(r);
	}

	@Override
	public Reply getReply(Reply reply) {

		return replyRepo.findById(reply.getRid()).get();

	}

}// class
