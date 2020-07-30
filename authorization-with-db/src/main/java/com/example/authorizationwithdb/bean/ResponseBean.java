package com.example.authorizationwithdb.bean;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBean {
    private Integer status;
    private String msg;
    private Object obj;
}
