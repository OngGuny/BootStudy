package kr.bbaa.board.attachfile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.bbaa.board.attachfile.entity.AttachFile;

public interface AttachFileRepository extends JpaRepository<AttachFile, Long>{

   @Query(value = " SELECT * FROM tbl_attachfile WHERE BOARD_SEQ = :seq ", nativeQuery = true)
   List<AttachFile> selectImageAllViewQuery(@Param("seq") Long seq);
   
}