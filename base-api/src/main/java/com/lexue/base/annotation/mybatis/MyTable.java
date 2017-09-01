package com.lexue.base.annotation.mybatis;

import java.lang.annotation.*;

/**
 * Mybatis的表名的注解
 * 
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTable {
	String value() default "";
}
