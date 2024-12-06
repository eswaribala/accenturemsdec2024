package com.siemens.orderservice.repositories;

import com.siemens.orderservice.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,Long> {
}
