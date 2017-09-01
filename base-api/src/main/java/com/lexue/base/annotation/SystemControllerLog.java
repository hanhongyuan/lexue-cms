package com.lexue.base.annotation;

import java.lang.annotation.*;

/**
 * Created by lilong on 17-7-3.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {

    String module()  default "";
    String method()  default "";

}
