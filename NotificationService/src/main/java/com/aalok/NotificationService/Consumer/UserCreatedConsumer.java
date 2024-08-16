package com.aalok.NotificationService.Consumer;

import com.aalok.Utilities.CommonConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedConsumer {
    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    public JavaMailSender sender;
    @Autowired
    public SimpleMailMessage simpleMailMessage;
    @KafkaListener(topics = {"USER_CREATED"},groupId ="notificationgroup" )
    public void sendNotification(String msg) throws JsonProcessingException {
        JSONObject jsonObject=objectMapper.readValue(msg,JSONObject.class);
        String name=(String) jsonObject.get(CommonConstants.USER_NAME);
        String email=(String) jsonObject.get(CommonConstants.USER_EMAIl);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("Team EWallet Welcomes You "+name+" to the Platform");
        simpleMailMessage.setFrom("ewallet@mail.com");
        simpleMailMessage.setSubject("EWallet Registration Successful");
        sender.send(simpleMailMessage);
    }
}
