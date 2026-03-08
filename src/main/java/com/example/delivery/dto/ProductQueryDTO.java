package com.example.delivery.dto;
import lombok.Data;

@Data
public class ProductQueryDTO {
    private Integer page = 1;

    private Integer pageSize = 10;

    private String keyword;
}
