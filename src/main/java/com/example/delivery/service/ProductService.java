package com.example.delivery.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.delivery.dto.ProductAddDTO;
import com.example.delivery.dto.ProductQueryDTO;
import com.example.delivery.dto.ProductUpdateDTO;
import com.example.delivery.entity.Product;

public interface ProductService {
    Page<Product> page(ProductQueryDTO dto);

    void add(ProductAddDTO dto);

    Product getDetail(Long id);

    void update(ProductUpdateDTO dto);

    void delete(Long id);

    void updateStatus(ProductUpdateDTO dto);
}
