package com.mybatis.core.steptwo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName MainApp
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 **/
public class MainApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/steptwo/applicationContext.xml");

        UserService userService = new UserService();
        System.out.println(userService.selectById(context,1));
        System.out.println(context.getBean("transactionManager"));
    }
}
