package com.example.emailservice.config;

import com.example.emailservice.dto.SendEmailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


import java.util.Properties;

@Service
public class SendEmailConsumer {

//    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SendEmailConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmailUtil emailUtil;

    @KafkaListener(topics = "send_email", groupId = "send_email_group")
    public void handleSendEmailTopic(String emailDetails) throws Exception{
        SendEmailDto sendEmailDto = null;
        try {
            sendEmailDto = objectMapper.readValue(emailDetails, SendEmailDto.class);

        } catch (Exception e) {
          throw new RuntimeException("Error parsing email details", e);
        }
        if(sendEmailDto == null){
            return;
        }
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        final String fromEmail = "bitanguharoy@gmail.com"; //requires valid gmail id
        final String password = "swor bdhf zbqf hbvv"; // correct password for gmail id
        final String toEmail = sendEmailDto.getTo();
        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        System.out.println("Session created");
        emailUtil.sendEmail(session, toEmail, sendEmailDto.getSubject(), sendEmailDto.getEmail());
    }
}
