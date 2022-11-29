package kr.bbaa.board.service;

import java.util.List;

import org.springframework.data.domain.Page;

import kr.bbaa.board.domain.Search;
import kr.bbaa.board.entity.Board;
import kr.bbaa.board.reply.entity.Reply;

public interface BoardService {

	public void insertBoard(Board board);

	public void updateBoard(Board board);

	public void deleteBoard(Board board);

	public Board getBoard(Board board);

	public Page<Board> getBoardList(Search search, int page);
	
	public void insertReply(Reply reply);
	
	public void deleteReply(Reply reply);
	
	public void updateReply(Reply reply);
	
	public Reply getReply(Reply reply);
	
	public void updateBoardReplyCnt(Board board);

	public Page<Reply> getReplyList(Board board, Search search, int page);

	public List<Board> getExcelBoardList();
}
