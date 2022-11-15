package com.brian.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) // 表示这个注解可以用用在类/接口上，还可以用在方法上
@Retention(RetentionPolicy.RUNTIME) // 表示这是一个运行时注解，即运行起来之后，才获取注解中的相关信息
@Inherited // 可以被子类继承
@Documented // 当执行javadoc的时候，本注解会生成相关文档
public @interface Custom {

    String ip();
    int port() default 3306;
}
