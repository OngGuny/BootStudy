package kr.bbaa.board.attachfile.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import kr.bbaa.board.attachfile.entity.AttachFile;
import kr.bbaa.board.entity.Board;

public interface AttachFileService {

   public Long saveFile(MultipartFile files, Board board) throws IOException;
   
   public List<AttachFile> view();
   
   public List<AttachFile> fileAllView(Long seq);
   
   public AttachFile downloadImage(@PathVariable("fileId") Long id);
   
}
