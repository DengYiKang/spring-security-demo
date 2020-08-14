package com.example.jwt01.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt01.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    /**
     * 将id存入token，以password作为秘钥加密
     */
    public String getToken(User user){
        String token="";
        token= JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
