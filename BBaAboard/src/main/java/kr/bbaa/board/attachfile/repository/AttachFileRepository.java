package kr.bbaa.board.attachfile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.bbaa.board.attachfile.entity.AttachFile;

public interface AttachFileRepository extends JpaRepository<AttachFile, Long>{

   @Query(value = " SELECT * FROM tbl_attachfile WHERE BOARD_SEQ = :seq ", nativeQuery = true)
   List<AttachFile> selectImageAllViewQuery(@Param("seq") Long seq);
   
   @Transactional
   @Modifying
   @Query("DELETE from tbl_attachfile a where a.board.seq = :boardseq")
   void deleteBoard(@Param("boardseq")Long seq);
}