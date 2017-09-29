package com.sundae.aoplib.annotation;

/**
 * Created by daijiyuan on 2017/9/29.
 * 邮箱 948820549@qq.com
 *
 * @Sundae
 */

public @interface InterceptBefore {
    String beforeMethodName() default "";
}
