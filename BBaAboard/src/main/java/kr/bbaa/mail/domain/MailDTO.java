package kr.bbaa.mail.domain;

import java.util.List;

import lombok.Data;

@Data
public class MailDTO {

   public static final long serialVersionUID = 283746339876L;

   public MailDTO() {
   }
   
   private String toAddress; //수신자
   private String title;	//제목
   private String message;	//내용
   private List<String> attachFileList;//첨부파일
   
//   public void setAttachFileList(AttachFile at) {
//	   this.attachFileList.add(at);
//   }
   
	public MailDTO(String to, String subject
			, String content, List<String> filePathList) { //여기서 널 처리 해줌 
		this.toAddress = (to==null) ? "noreceiver@noreceiver.com" : to;
		this.title = (subject==null) ? "제목 없음" : subject;
		this.message = (content==null) ? "내용 없음" : content;
		this.attachFileList = filePathList;
	}
} // class
