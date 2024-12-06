package com.siemens.cartservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siemens.cartservice.dtos.GenericMessage;
import com.siemens.cartservice.models.Cart;
import com.siemens.cartservice.services.CartPublishingService;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("carts")
public class CartController {
    @Autowired
    private CartPublishingService cartPublishingService;

    @PostMapping("/v1.0")
    public CompletableFuture<ResponseEntity<String>> publishCartData(@RequestBody Cart cart) throws JsonProcessingException {

       return cartPublishingService.publishCartData(cart)
                .thenApply(result->ResponseEntity.status(HttpStatus.OK)
                .body(result.getRecordMetadata().topic()+","+result.getRecordMetadata().partition()+","+result.getRecordMetadata().offset()))
                .exceptionally(ex-> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
                });

    }
}
