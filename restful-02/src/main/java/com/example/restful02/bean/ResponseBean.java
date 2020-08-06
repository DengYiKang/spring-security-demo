package com.example.restful02.bean;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBean {
    private Integer status;
    private String msg;
    private Object obj;
}
