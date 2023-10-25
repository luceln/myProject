package com.library.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解用在方法上，用于标识需要切入的方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalInterceptor {
    /**
     * 校验超级管理员
     */
    boolean checkAdmin() default false;

    /**
     * 校验登录
     */
    boolean checkLogin() default true;

    /**
     * 校验参数
     */
    boolean checkParams() default false;
}
