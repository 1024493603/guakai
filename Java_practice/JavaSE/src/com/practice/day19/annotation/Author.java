package com.practice.day19.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    //注解属性
    //1.修饰符为默认或者public
    //2.不能有方法体
    String name() default "";

    int age() default 30;   // 带默认值的注解;  使用的时候就可以不写此属性值;

    //如果不指定赋值给那个属性则默认给value赋值
    //赋值多个时value必须加
    String value();
}
