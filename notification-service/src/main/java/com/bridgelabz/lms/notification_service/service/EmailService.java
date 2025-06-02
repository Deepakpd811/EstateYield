package com.bridgelabz.lms.notification_service.service;

import com.bridgelabz.lms.notification_service.OtpRequestDto;
import com.bridgelabz.lms.notification_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void receiveOtpMessage(OtpRequestDto otp) {
        String toEmail = otp.getEmail();
        String code = otp.getOtp();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Your OTP Code");
        mailMessage.setText("Your OTP is: " + code);

        mailSender.send(mailMessage);
    }
}
