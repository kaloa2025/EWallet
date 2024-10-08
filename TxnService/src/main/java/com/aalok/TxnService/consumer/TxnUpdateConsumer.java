package com.aalok.TxnService.consumer;

import com.aalok.Utilities.CommonConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TxnUpdateConsumer
{
    @KafkaListener(topics = CommonConstants.TXN_UPDATED_TOPIC, groupId = "txn-group")
    public void updateTxn(String msg) throws JsonProcessingException {
//        if status is succes / failed --> update the db (txn)
        // send notification the user
        System.out.println("I got the msg here ");
    }
}
