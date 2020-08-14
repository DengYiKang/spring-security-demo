package com.example.jwt01.service;

import com.example.jwt01.entity.User;
import com.example.jwt01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.selectUserByName(username);
    }

    public User findUserById(String id) {
        return userMapper.selectUserById(id);
    }
}
