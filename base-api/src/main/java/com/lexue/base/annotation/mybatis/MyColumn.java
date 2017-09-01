package com.lexue.base.annotation.mybatis;

import java.lang.annotation.*;

/**
 * Mybatis的列名的注解
 * 
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyColumn {
	String value() default "";
}
