package kr.kwangan2.board.exception.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.kwangan2.board.exception.BoardException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BoardException.class) //위에다가는 익셉션 타입 넣어줌
	public String HandleBoardException(BoardException exception, Model model) {
		model.addAttribute("exception",exception);
		return "/exception/boardException";
	}

	@ExceptionHandler(Exception.class) //여기다 익셉션 넣어놓고 인자로 보드익셉션 받으니까 안넘어가지 ㅋㅋㅋㅋㅋ
	public String HandleGLobalException(Exception exception, Model model) {
		model.addAttribute("exception",exception);
		return "/exception/globalException";
	}
	
	
}//class
