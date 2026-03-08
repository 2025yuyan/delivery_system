package com.example.delivery.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.delivery.common.Result;
import com.example.delivery.dto.ProductAddDTO;
import com.example.delivery.dto.ProductQueryDTO;
import com.example.delivery.dto.ProductUpdateDTO;
import com.example.delivery.entity.Product;
import com.example.delivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Result<?> page(ProductQueryDTO dto) {
        Page<Product> page= productService.page(dto);
        return Result.success(page);
    }

    @PostMapping
    public Result<?> add(@RequestBody ProductAddDTO dto){

        productService.add(dto);

        return Result.success("success");

    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id){

        Product product= productService.getDetail(id);

        return Result.success(product);
    }

    @PutMapping
    public Result<?> update(@RequestBody ProductUpdateDTO dto){
        productService.update(dto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        productService.delete(id);
        return Result.success();
    }

    @PostMapping("/status")
    public Result<?> updateStatus( @RequestBody ProductUpdateDTO dto){
        productService.updateStatus(dto);
        return Result.success();
    }
}
