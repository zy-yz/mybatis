package com.zy.handler;

import com.zy.annotation.PepInsert;
import com.zy.annotation.PepParam;
import com.zy.annotation.PepQuery;
import com.zy.utils.JDBCUtils;
import com.zy.utils.SQLUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName UserMapperInvocationHandler
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 **/
public class UserMapperInvocationHandler implements InvocationHandler {

    private Class userMapperClazz;

    public UserMapperInvocationHandler(Class userMapperClazz) {
        this.userMapperClazz = userMapperClazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        PepQuery pepQuery = method.getDeclaredAnnotation(PepQuery.class);
        //查询
        if(null != pepQuery){
            return getResult(method,pepQuery,args);
        }
        //插入
        PepInsert pepInsert = method.getDeclaredAnnotation(PepInsert.class);
        if(null != pepInsert){
            String insertSql = pepInsert.value();
            //插入参数
            String[] insertParam = SQLUtils.getInsertParams(insertSql);
            //参数绑定
            ConcurrentHashMap<String,Object> paramMap = getmethodParam(method,args);
            //将参数值加入lise中
            List<Object> paramValueList = addParamToList(insertParam, paramMap);

            insertSql = SQLUtils.replaceParam(insertSql, insertParam);
            return JDBCUtils.insert(insertSql, false, paramValueList);
        }


        return null;
    }



    private Object getResult(Method method,PepQuery pepQuery,Object[] args) throws SQLException, IllegalAccessException, InstantiationException {
        String querySql = pepQuery.value();
        //获取SQL参数
        List<Object> paramList= SQLUtils.getSelectParams(querySql);
        //替换SQL参数
        querySql = SQLUtils.replaceParam(querySql,paramList);

        //获取方法参数,绑定值
        ConcurrentHashMap<String,Object> paramMap = getmethodParam(method,args);

        List<Object> paramValueList = new ArrayList<>();
        //将参数值装入list集合
        for (Object param: paramList){
            Object paramValue = paramMap.get(param);
            paramValueList.add(paramValue);
        }
        System.out.println("paramValueList:" + paramValueList);
        ResultSet rs = JDBCUtils.query(querySql, paramValueList);
        if (!rs.next()) {return null;}

        Class<?> returnTypeClazz = method.getReturnType();
        Object obj = returnTypeClazz.newInstance();
        //光标往前移动一位
        rs.previous();
        while (rs.next()) {
            Field[] fields = returnTypeClazz.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                String fieldValue = rs.getString(fieldName);
                field.setAccessible(true);
                field.set(obj, fieldValue);
            }
        }
        return obj;

    }

    private ConcurrentHashMap getmethodParam(Method method,Object[] args){
        ConcurrentHashMap paramMap = new ConcurrentHashMap();
        Parameter[] parameters = method.getParameters();
        for (int i=0;i<parameters.length;i++){
            PepParam pepParam = parameters[i].getAnnotation(PepParam.class);
            if(null == pepParam){
                continue;
            }
            String paramName = pepParam.value();
            Object paramValue = args[i];
            paramMap.put(paramName,paramValue);
        }
        return paramMap;
    }

    private List<Object> addParamToList(String[] insertParam, ConcurrentHashMap<String, Object> paramMap) {
        List<Object> paramValueList = new ArrayList<>();
        for (String param : insertParam) {
            Object paramValue = paramMap.get(param.trim());
            paramValueList.add(paramValue);
        }
        return paramValueList;
    }


}
