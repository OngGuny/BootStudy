package kr.bbaa.excel.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ExcelService {

	public void download(HttpServletResponse response) throws IOException;

}
