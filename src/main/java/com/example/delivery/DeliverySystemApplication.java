package com.example.delivery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.example.delivery.mapper")

public class DeliverySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverySystemApplication.class, args);

    }

}
