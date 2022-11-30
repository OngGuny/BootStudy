package kr.bbaa.mail.component;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import kr.bbaa.mail.domain.MailDTO;
import lombok.AllArgsConstructor;


@Component
public class SMTPGMailSender {

	@Autowired
	private JavaMailSender jMailSender;
	
	@Value("${google.mail.sender.email}")
	private String sender; // 발신자 메일주소
	
	// 첨부파일이 없는 메일 발송
		public void sendSimpleMessage(SimpleMailMessage message) {

//			SimpleMailMessage message = new SimpleMailMessage();
System.out.println(message);
//			message.setFrom(sender);
//			message.setTo(mailDTO.getToAddress());
//			message.setSubject(mailDTO.getTitle());
//			message.setText(mailDTO.getMessage());
			jMailSender.send(message);
		}
		
		// 첨부파일이 있는 메일 발송
		public void sendMessageWithAttachment(MailDTO mailDTO) throws Exception {

			MimeMessage message = jMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(sender); //발신자 메일 주소 
			helper.setTo(mailDTO.getToAddress());
			helper.setSubject(mailDTO.getTitle());
			helper.setText(mailDTO.getMessage());

			File file = new File(mailDTO.getFilePath());
			FileSystemResource fileSystemResource 
				= new FileSystemResource(file);
			helper.addAttachment(file.getName(), fileSystemResource);

			jMailSender.send(message);
		}
	
}//class
