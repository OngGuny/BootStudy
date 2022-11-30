package kr.bbaa.board.attachfile.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import kr.bbaa.board.attachfile.entity.AttachFile;
import kr.bbaa.board.attachfile.repository.AttachFileRepository;
import kr.bbaa.board.attachfile.service.AttachFileService;
import kr.bbaa.board.entity.Board;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AttachFileServiceImpl implements AttachFileService {

	  @Value("${file.dir}")
	   private String fileDir;

	   private final AttachFileRepository attachFileRepo;

	   public Long saveFile(MultipartFile files, Board board) throws IOException {
	      if (files.isEmpty()) {
	         return null;
	      }
	      String origName = files.getOriginalFilename();
	      String uuid = UUID.randomUUID().toString();
	      String extension = origName.substring(origName.lastIndexOf("."));
	      String savedName = uuid + extension;
	      String savedPath = fileDir + savedName;
	      
	      AttachFile file = AttachFile.builder().orgNm(origName)
	                                            .savedNm(savedName)
	                                            .savedPath(savedPath)
	                                            .build();
	      file.setBoard(board);
	      files.transferTo(new File(savedPath));
	      
	      AttachFile savedFile = attachFileRepo.save(file);
	      
	      return savedFile.getFileId();
	   }
	   
	   
	   //findByID 에서 보드의 SEQ를 메게변수로 받아와서 찾아오기 
	   
	   public List<AttachFile> view() {
	      return attachFileRepo.findAll();
	   }
	   
	   public AttachFile downloadImage(@PathVariable("fileId") Long id) {
	      return attachFileRepo.findById(id).orElse(null);
	   }


	   @Override
	   public List<AttachFile> fileAllView(Long seq) {
	      System.out.println("===========>list");
	      return attachFileRepo.selectImageAllViewQuery(seq);
	   }

	}