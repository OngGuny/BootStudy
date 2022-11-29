package kr.bbaa.mail.service;

import kr.bbaa.mail.domain.MailDTO;

public interface MailService {
	public abstract void sendSimpleMail(MailDTO mailDTO);

	public abstract void sendAttachMail(MailDTO mailDTO);

	public void mailSend(MailDTO mailDTO);
}