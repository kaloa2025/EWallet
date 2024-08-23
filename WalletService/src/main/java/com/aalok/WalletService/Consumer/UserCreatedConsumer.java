package com.aalok.WalletService.Consumer;

import com.aalok.Utilities.CommonConstants;
import com.aalok.WalletService.Repo.WalletRepo;
import com.aalok.WalletService.model.Wallet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedConsumer {
    @Value("${user.creation.time.balance}")
    private Double balance;
    @Autowired
    public WalletRepo walletRepo;
    @Autowired
    public ObjectMapper objectMapper;
    private static Logger logger= LoggerFactory.getLogger(UserCreatedConsumer.class);
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @KafkaListener(topics = CommonConstants.USER_CREATED_TOPIC,groupId = "walletgroup")
    public void createdWallet(String msg) throws JsonProcessingException {
        JSONObject object=objectMapper.readValue(msg,JSONObject.class);
        Integer userId=(Integer) object.get(CommonConstants.USER_ID);
        String contact=(String) object.get(CommonConstants.USER_CONTACT);
        Wallet wallet=Wallet.builder()
                .contact(contact).balance(balance).userId(userId).build();
        walletRepo.save(wallet);
        logger.info("wallet Created ============ ");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(CommonConstants.USER_ID,userId);
        jsonObject.put(CommonConstants.WALLET_BALANCE,balance);
        kafkaTemplate.send(CommonConstants.WALLET_CREATED_TOPIC,objectMapper.writeValueAsString(jsonObject));

        logger.info("produced the wallet creating message in the queue for user id" + userId);

    }
}
