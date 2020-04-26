package com.peppers.mybatisexample.mapper;

import com.peppers.mybatisexample.eneity.Entity;

import java.util.List;

/**
 * @ClassName ConcatMapper
 * @Author peppers
 * @Date 2020/4/26
 * @Description 模糊查询
 **/
public interface ConcatMapper {

    public List<Entity> queryByName(String name);
}
