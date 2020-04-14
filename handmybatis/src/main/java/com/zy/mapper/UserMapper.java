package com.zy.mapper;

import com.zy.annotation.PepInsert;
import com.zy.annotation.PepParam;
import com.zy.annotation.PepQuery;
import com.zy.bean.User;

public interface UserMapper {
    @PepQuery("select username, city from user1  where username = #{username} and city = #{city}")
    User selectUser(@PepParam("username") String name, @PepParam("city") String city);


    @PepInsert("insert into user1(username, city) values(#{username}, #{city})")
    int insertUser(@PepParam("username") String name, @PepParam("city") String city);


}
