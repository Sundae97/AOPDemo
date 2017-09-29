package com.sundae.aoplib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by daijiyuan on 2017/9/29.
 * 邮箱 948820549@qq.com
 *
 * @Sundae
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD , ElementType.CONSTRUCTOR})
public @interface DebugTrack {

}
