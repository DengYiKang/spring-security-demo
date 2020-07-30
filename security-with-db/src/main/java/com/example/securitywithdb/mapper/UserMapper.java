package com.example.securitywithdb.mapper;

import com.example.securitywithdb.bean.MyUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user_for_role where username = #{username}")
    MyUserBean selectByUserName(@Param("username") String username);
}
