package kr.bbaa.board.domain;

import lombok.Data;

@Data
public class Search {
	private String searchCondition;
	private String searchKeyword;
	private String classification;//검색 조건 유지용
	private int page;
	private int recordSize;
	private int pageSize;
	private int replyPage;
}
