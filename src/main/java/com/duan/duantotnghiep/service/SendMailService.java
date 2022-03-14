package com.duan.duantotnghiep.service;


import com.duan.duantotnghiep.entites.MailInfo;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SendMailService {

	void run();

	void queue(String to, String subject, String body);

	void queue(MailInfo mail);

	void send(MailInfo mail) throws MessagingException, IOException;
}
