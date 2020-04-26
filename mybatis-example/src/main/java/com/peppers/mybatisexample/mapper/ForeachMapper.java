package com.peppers.mybatisexample.mapper;

import com.peppers.mybatisexample.eneity.Entity;

import java.util.List;

/**
 * @ClassName ForeachMapper
 * @Author peppers
 * @Date 2020/4/26
 * @Description
 **/
public interface ForeachMapper {
    public List<Entity> queryById(List<String> userids);
}
