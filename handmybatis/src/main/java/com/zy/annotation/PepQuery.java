package com.zy.annotation;

import java.lang.annotation.*;

/**
 * @ClassName PepQuery
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PepQuery {
    String value() default "";
}
