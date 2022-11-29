package kr.bbaa.mail.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.bbaa.mail.domain.MailDTO;
import kr.bbaa.mail.service.MailService;

@Service

public class MailServiceImpl implements MailService {
	 
	
	private JavaMailSender mailSender;
	   private static final String From_ADDRESS = "luster308@gmail.com";
	   
	   @Override
	   public void mailSend(MailDTO mailDTO) {
	      SimpleMailMessage message = new SimpleMailMessage();
	      message.setTo(mailDTO.getAddress());
	      message.setFrom(MailServiceImpl.From_ADDRESS);
	      message.setSubject(mailDTO.getTitle());
	      message.setText(mailDTO.getMessage());
	      
	      mailSender.send(message);
	   }

	@Override
	public void sendSimpleMail(MailDTO mailDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAttachMail(MailDTO mailDTO) {
		// TODO Auto-generated method stub
		
	}
	   
	   
	   
	   
}
