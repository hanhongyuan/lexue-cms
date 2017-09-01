package com.lexue.base.annotation.mybatis;

import java.lang.annotation.*;

@Documented  
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyId {
	String value() default "";
}
