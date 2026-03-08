package com.example.delivery.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Integer status;
    }
