package com.example.delivery.controller;


import com.example.delivery.common.Result;
import com.example.delivery.dto.UserLoginDTO;
import com.example.delivery.dto.UserRegisterDTO;
import com.example.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody UserRegisterDTO dto){
        userService.register(dto);
        return Result.success(null);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserLoginDTO dto){
        String result = userService.login(dto);
        return Result.success(result);
    }
}
