package kr.bbaa.mail.domain;

import lombok.Data;

@Data
public class MailDTO {

   public static final long serialVersionUID = 283746259876L;

   public MailDTO() {
   }
   
   private String address;
   private String title;
   private String message;

} // class
