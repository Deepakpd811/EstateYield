package com.bridgelabz.lms.notification_service.controller;

import com.bridgelabz.lms.notification_service.OtpRequestDto;
import com.bridgelabz.lms.notification_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public NotificationController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send-otp")
    public void sendOtpNotification(@RequestBody OtpRequestDto otp) {

        rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFICATION_QUEUE, otp); // Sending to the queue

        //return ResponseEntity.ok("OTP notification sent to queue");
    }
}
