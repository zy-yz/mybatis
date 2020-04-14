package com.mybatis.core.steptwo;

import org.springframework.context.ApplicationContext;

/**
 * @ClassName UserService
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 **/
public class UserService {

    public User selectById(ApplicationContext context,int id){
        UserDao userDao = context.getBean(UserDao.class);
        User user = userDao.selectById(1);
        System.out.println(user);
        return user;
    }
}
