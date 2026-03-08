package com.example.delivery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.delivery.entity.Product;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
