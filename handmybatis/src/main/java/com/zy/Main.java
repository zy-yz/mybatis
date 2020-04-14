package com.zy;

import com.zy.bean.User;
import com.zy.mapper.UserMapper;
import com.zy.sqlsession.SqlSession;

import java.sql.SQLException;

/**
 * @ClassName Main
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 **/
public class Main {

    public static void main(String[] args) throws SQLException {
        UserMapper userMapper = SqlSession.getUserMapper(UserMapper.class);
        User user = userMapper.selectUser("张三", "nong");
       // userMapper.insertUser("张三丰","nong");
        int row = userMapper.insertUser("Peppers", "chongQing");
        System.out.println("row:" + user);
    }

}
