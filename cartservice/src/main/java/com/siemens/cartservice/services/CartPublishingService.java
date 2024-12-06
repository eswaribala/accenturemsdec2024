package com.siemens.cartservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.siemens.cartservice.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CartPublishingService {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    @Value("${topicname}")
    private String topicName;

    public CompletableFuture<SendResult<Object,Object>> publishCartData(Cart cart) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(cart);
       return kafkaTemplate.send(topicName,json);

    }

}
