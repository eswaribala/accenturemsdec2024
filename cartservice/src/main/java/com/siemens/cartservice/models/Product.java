package com.siemens.cartservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long productId;
    private String name;
    private long cost;
    private long unit;

}
