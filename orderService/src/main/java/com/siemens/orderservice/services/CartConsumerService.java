package com.siemens.orderservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.orderservice.models.Cart;
import com.siemens.orderservice.repositories.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "siemenscartnov2024",groupId = "saga-ecommerce-group")
@Slf4j
public class CartConsumerService {
    @Autowired
    private CartRepository cartRepository;

    @KafkaHandler(isDefault = true)
    public void receiveCartData(String data) throws JsonProcessingException {
        log.info(data);
        ObjectMapper objectMapper=new ObjectMapper();
        Cart cart= objectMapper.readValue(data, Cart.class);
       cart.setTotalAmount(cart.getProducts()
               .stream().map(p->p.getCost()).reduce(0L,Long::sum));

        cartRepository.save(cart);
    }
}
