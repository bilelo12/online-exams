package com.bilelhami.exam.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private  JavaMailSender javaMailSender ;



    public void sendInvitationEmail(String to, long examId) {
        String subject = "Invitation to Exam";
        String body = "You have been invited to participate in Exam #" + examId + ". Click the link to join.";

        sendSimpleEmail(to, subject, body);
    }

    private void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}
