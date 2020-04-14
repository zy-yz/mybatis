package com.zy.annotation;

import java.lang.annotation.*;

/**
 * @ClassName PepInsert
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PepInsert {

    String value() default "";
}