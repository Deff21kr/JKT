package org.zerock.myapp.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service("mailSendService")
@NoArgsConstructor
@Log4j2
//@Component
public class MailSendService {
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	private Integer authNumber;
	
	public void makeRandomNumber() {
		Random num = new Random();
		int checkNum = num.nextInt(888888)+111111;
		log.info("\n\n인증번호 : {} ",checkNum);
		authNumber = checkNum;
	}
	
	public String joinEmail (String EMail) throws ServiceException {
		
		makeRandomNumber();
		
		String setFrom = ".com";
		String toMail = EMail;
		String title = "회원가입 인증 이메일입니다.";
		String content ="<br><br>"+"인증번호는 < "+authNumber +" > 입니다.";
//		this.mailSender.mail
		mailSend(toMail, title, content);
		return Integer.toString(authNumber);
	}
	
	public void mailSend (String toMail,String title, String content ) throws ServiceException {
		MimeMessage message = this.mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			this.mailSender.send(message);
			
		} catch (Exception e ) {
			throw new ServiceException(e);
		}
		
	}
	
	

}
