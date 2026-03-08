package com.example.delivery.service;

import com.example.delivery.dto.UserLoginDTO;
import com.example.delivery.dto.UserRegisterDTO;

public interface UserService {

    void register(UserRegisterDTO dto);

    String login(UserLoginDTO dto);

}
