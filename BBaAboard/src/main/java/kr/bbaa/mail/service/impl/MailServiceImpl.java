package kr.bbaa.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.bbaa.mail.component.SMTPGMailSender;
import kr.bbaa.mail.domain.MailDTO;
import kr.bbaa.mail.service.MailService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

	@Autowired
	private SMTPGMailSender mailSender;

	private static final String From_ADDRESS = "luster308@gmail.com";

	public void sendSimpleMail(MailDTO mailDTO) {
	      SimpleMailMessage message = new SimpleMailMessage();
	      message.setTo(mailDTO.getToAddress());
	      message.setFrom(MailServiceImpl.From_ADDRESS);
	      message.setSubject(mailDTO.getTitle());
	      message.setText(mailDTO.getMessage());
	      
	      mailSender.sendSimpleMessage(message);
	   }
    
	      
	@Override
	public void sendAttachMail(MailDTO mailDTO) {
		try {
			mailSender.sendMessageWithAttachment(mailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
