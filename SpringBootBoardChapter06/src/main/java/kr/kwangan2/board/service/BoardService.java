package kr.kwangan2.board.service;

import java.util.List;

import kr.kwangan2.board.entity.Board;

public interface BoardService {

	public List<Board> listBoard(Board board);

	public void insertBoard(Board board);

	public Board getBoard(Board board);

	public void updateBoardProc(Board board);

	public void deleteBoard(Board board);

}