package kr.bbaa.excel.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.bbaa.board.entity.Board;
import kr.bbaa.board.service.BoardService;
import kr.bbaa.excel.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService{
	
    @Autowired
    BoardService boardService;
    @Override
    public void downloadBoardList(HttpServletResponse response) throws IOException {


        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;
 
        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        cell.setCellValue("ID");
        cell = row.createCell(cellNum++);
        cell.setCellValue("조회수");
        cell = row.createCell(cellNum++);
        cell.setCellValue("내용");
        cell = row.createCell(cellNum++);
        cell.setCellValue("날짜");
        cell = row.createCell(cellNum++);
        cell.setCellValue("제목");
        cell = row.createCell(cellNum++);
        cell.setCellValue("아이디");

        List<Board> boardList = boardService.getExcelBoardList() ;
        // Body
        for (Board resultBoard : boardList) {
            cellNum = 0;

            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue(resultBoard.getSeq());

            cell = row.createCell(cellNum++);
            cell.setCellValue(resultBoard.getCnt());

            cell = row.createCell(cellNum++);
            cell.setCellValue(resultBoard.getContent());

            cell = row.createCell(cellNum++);
            cell.setCellValue(String.format(resultBoard.getCreateDate().toString()));

            cell = row.createCell(cellNum++);
            cell.setCellValue(resultBoard.getTitle());

            cell = row.createCell(cellNum++);
            cell.setCellValue(resultBoard.getMember().getName());
        }
         
        // Download	
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=boardList.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
 
    }//boardlist
    
    //멤버 목록 다운로드 메소드
    
}//classs