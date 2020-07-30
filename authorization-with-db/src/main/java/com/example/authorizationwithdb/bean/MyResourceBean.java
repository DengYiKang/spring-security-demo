package com.example.authorizationwithdb.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MyResourceBean {
    private Long id;
    private String url;
    private String roles;

    public String[] getRolesArray() {
        String[] authorities = roles.split(",");
        return authorities;
    }
}
