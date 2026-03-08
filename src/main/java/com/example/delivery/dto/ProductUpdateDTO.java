package com.example.delivery.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDTO {
    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

}
