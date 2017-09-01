package com.lexue.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

/**
 * spring的工具类
 * <P>
 * 
 */
@Lazy
@Singleton
@Component
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringUtil.ctx = applicationContext;
	}

	/**
	 * 根据名称获取Bean
	 * 
	 * @param name
	 *            Bean的名称
	 * 
	 * @return 获取到的Bean对象，类型为Object
	 */
	public static Object getBean(String name) {
		return ctx.getBean(name);
	}

	/**
	 * 根据类型获取Bean
	 * 
	 * @param clazz
	 *            Bean类型
	 * 
	 * @return 获取到的Bean对象
	 */
	public static <T> T getBean(Class<T> clazz) {
		return ctx.getBean(clazz);
	}

}
