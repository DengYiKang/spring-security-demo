package com.example.authorizationwithdb.mapper;

import com.example.authorizationwithdb.bean.MyResourceBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ResourceMapper {
    @Select("select * from resource")
    List<MyResourceBean> selectAllResources();
}
