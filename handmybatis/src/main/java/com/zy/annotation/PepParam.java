package com.zy.annotation;

import java.lang.annotation.*;

/**
 * @ClassName PepParam
 * @Author peppers
 * @Date 2020/4/14
 * @Description
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface PepParam {
    String value() default "";

}
