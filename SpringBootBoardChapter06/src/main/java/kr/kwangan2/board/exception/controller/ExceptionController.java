package kr.kwangan2.board.exception.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kwangan2.board.exception.BoardNotFoundException;


@Controller
@RequestMapping("/exception/")
public class ExceptionController {

	@RequestMapping("boardException")
	public String boardError() {
		throw new BoardNotFoundException("검색된 게시글이 없습니다.");
	}

	@RequestMapping("illegalArgumentException")
	public String ellegalArgumentError() {
		throw new IllegalArgumentException("부적절한 인자가 전달되었습니다.");
	}

	@RequestMapping("sqlException")
	public String sqlError() throws SQLException {
		throw new SQLException("SQL 구문에 오류가 있습니다.");
	}
	
	@ExceptionHandler(SQLException.class)
	public String numberFormatError(SQLException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/sqlException";
	}

}
