package com.zy.sqlsession;

import com.zy.handler.UserMapperInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * @ClassName SqlSession
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 **/
public class SqlSession {

    public static <T> T getUserMapper(Class clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[] {clazz},new
                UserMapperInvocationHandler(clazz));
    }

}
