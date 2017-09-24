package com.sundae.aoptest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by daijiyuan on 2017/9/22.
 * 邮箱 948820549@qq.com
 *
 * @Sundae
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR ,ElementType.METHOD})
public @interface CheckNet {
}
