package com.lexue.base.annotation.ssoauth;

import java.lang.annotation.*;

/**
 * 权限标识自定义注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    String value();
}
