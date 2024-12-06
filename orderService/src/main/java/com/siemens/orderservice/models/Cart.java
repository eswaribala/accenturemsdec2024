package com.siemens.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private long cartId;
    private List<Product> products;
    private long totalAmount;

}
