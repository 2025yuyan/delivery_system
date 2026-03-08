package com.example.delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.delivery.dto.UserLoginDTO;
import com.example.delivery.dto.UserRegisterDTO;
import com.example.delivery.entity.User;
import com.example.delivery.mapper.UserMapper;
import com.example.delivery.service.UserService;
import com.example.delivery.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserRegisterDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);

    }

    @Override
    public String login(UserLoginDTO dto) {


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",dto.getUsername());

        User user = userMapper.selectOne(queryWrapper);

        if(user==null){
            return "用户不存在";
        }
        if(!user.getPassword().equals(dto.getPassword())){
            return "密码错误";
        }


        return JwtUtils.generateToken(user.getId());
    }
}
