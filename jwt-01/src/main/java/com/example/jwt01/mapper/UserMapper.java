package com.example.jwt01.mapper;

import com.example.jwt01.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select id, username, password from user_for_role where id = #{id}")
    User selectUserById(@Param("id") String id);

    @Select("select id, username, password from user_for_role where username = #{username}")
    User selectUserByName(@Param("username") String username);
}
