package kr.kwangan2.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kwangan2.board.entity.Board;
import kr.kwangan2.board.repository.BoardRepository;
import kr.kwangan2.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;

	@Override
	public List<Board> listBoard(Board board) {
		return (List<Board>) boardRepo.findAll();
	}
	
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public Board getBoard(Board board) {

		return boardRepo.findById(board.getSeq()).get();
	}
	@Override
	public void updateBoardProc(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();

		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
//		findBoard.setCnt(board.getCnt());
		boardRepo.save(findBoard);
	}
	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}

}
