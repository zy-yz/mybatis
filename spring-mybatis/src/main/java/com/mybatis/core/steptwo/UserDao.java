package com.mybatis.core.steptwo;

import org.mybatis.spring.SqlSessionTemplate;
/**
 * @ClassName UserDao
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 *   SqlSessionTemplate 是 mybatis-spring的核心，其实现了SqlSession接口。
 *   在使用了SqlSessionTemplate之后，我们不再需要通过SqlSessionFactory.openSession()方法来创建SqlSession实例；
 *   使用完成之后，也不要调用SqlSession.close()方法进行关闭。
 *   另外，对于事务，SqlSessionTemplate 将会保证使用的 SqlSession 是和当前 Spring 的事务相关的。
 *
 *   之后我们可以在org.mybatis.spring.dao.UserDao类中直接进行注入。
 *   SqlSessionTemplate 是线程安全的, 可以被多个 DAO 所共享使用,SqlSessionTemplate本质上是一个代理.
 *  /
 **/
public class UserDao {

    private static String NAMESPACE = "com.mybatis.core.steptwo.mapper.UserMapper";

    private SqlSessionTemplate sqlsessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlsessionTemplate){
        this.sqlsessionTemplate = sqlsessionTemplate;
    }

    public User selectById(int id){
        User user = sqlsessionTemplate.selectOne(NAMESPACE + ".selectById",id);
        return user;
    }



}
