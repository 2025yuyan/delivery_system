package com.example.delivery.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.delivery.dto.ProductAddDTO;
import com.example.delivery.dto.ProductQueryDTO;
import com.example.delivery.dto.ProductUpdateDTO;
import com.example.delivery.entity.Product;
import com.example.delivery.mapper.ProductMapper;
import com.example.delivery.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Page<Product> page(ProductQueryDTO dto) {

        String key = "product:list:" + dto.getPage() + ":" +  dto.getPageSize();
        @SuppressWarnings("unchecked")
        Page<Product> cache = (Page<Product>) redisTemplate.opsForValue().get(key);

        if(cache != null){
            return cache;
        }
        Page<Product> page = new Page<>(dto.getPage(), dto.getPageSize());

        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();

        if(StringUtils.hasText(dto.getKeyword())){
            wrapper.like(Product::getName,dto.getKeyword());
        }

        wrapper.orderByDesc(Product::getCreateTime);
        return super.page(page, wrapper);
    }

    @Override
    public void add(ProductAddDTO dto) {
        Product product = new Product();

        BeanUtils.copyProperties(dto,product);

        product.setCreateTime(LocalDateTime.now());

        Set<String> keys = redisTemplate.keys("product:list*");
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }

        this.save(product);
    }

    @Override
    public Product getDetail(Long id) {

        String key = "product:detail:" + id;

        Product product = (Product) redisTemplate.opsForValue().get(key);

        if(product != null){return product;}

        product = super.getById(id);

        redisTemplate.opsForValue().set(key,product,30, TimeUnit.MINUTES);

        return product;


    }

    @Override
    public void update(ProductUpdateDTO dto) {
        Product  product = new Product();

        BeanUtils.copyProperties(dto,product);

        this.updateById(product);

    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }

    @Override
    public void updateStatus(ProductUpdateDTO dto) {
        Product product = new Product();

        product.setId(dto.getId());
        product.setStatus(dto.getStatus());

        this.updateById(product);
    }


}
