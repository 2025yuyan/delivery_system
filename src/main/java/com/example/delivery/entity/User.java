package com.example.delivery.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private long id;
    private String username;
    private String password;
    private String phone;
    private LocalDateTime createTime;

}
