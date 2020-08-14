package com.example.jwt01.api;

import com.alibaba.fastjson.JSONObject;
import com.example.jwt01.annotation.UserLoginToken;
import com.example.jwt01.entity.User;
import com.example.jwt01.service.TokenService;
import com.example.jwt01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserApi {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public Object login(User user) {
        JSONObject jsonObject = new JSONObject();
        User userInDb = userService.findByUsername(user.getUsername());
        if (userInDb == null) {
            jsonObject.put("message", "登录失败，用户不存在");
            return jsonObject;
        } else {
            if (!userInDb.getPassword().equals(user.getPassword())) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = tokenService.getToken(userInDb);
                jsonObject.put("token", token);
                jsonObject.put("user", userInDb);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }
}
