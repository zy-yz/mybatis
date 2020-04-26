package com.peppers.mybatisexample.mapper;

import com.peppers.mybatisexample.eneity.Entity;

import java.util.List;

/**
 * @ClassName ChooseMapper
 * @Author peppers
 * @Date 2020/4/26
 * @Description
 **/
public interface ChooseMapper {

    public List<Entity> getUserList_choose(Entity entity);
}
