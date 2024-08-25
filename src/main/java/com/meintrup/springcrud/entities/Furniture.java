package com.meintrup.springcrud.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Furniture {
    private int id;
    private String product;
    private BigDecimal price;
}
