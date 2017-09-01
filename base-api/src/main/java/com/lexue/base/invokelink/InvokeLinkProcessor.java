package com.lexue.base.invokelink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

/**
 * Created by zhao.weiwei on 2017/6/9.
 */
//@Configuration
public class InvokeLinkProcessor implements BeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object current = bean;
        Class<?> clazz = bean.getClass();
        if (AopUtils.isAopProxy(bean))
            clazz = AopUtils.getTargetClass(bean);
        try {
            if (AopUtils.isCglibProxy(bean))
                current = getCglibProxyTargetObject(bean);
            else if (AopUtils.isJdkDynamicProxy(bean))
                current = getJdkDynamicProxyTargetObject(bean);
        } catch (Exception e) {
            logger.error("", e);
        }
        try {
            for (Field field : clazz.getDeclaredFields()) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                if (autowired != null) {
                    Class<?> fieldType = field.getType();
                    FeignClient feignClient = fieldType.getAnnotation(FeignClient.class);
                    if (feignClient != null) {
                        field.setAccessible(true);
                        Object obj = field.get(current);
                        Field proxyFiled = obj.getClass().getSuperclass().getDeclaredField("h");
                        proxyFiled.setAccessible(true);
                        MyHystrixInvocationHandler myHystrixInvocationHandler = new MyHystrixInvocationHandler(proxyFiled.get(obj));
                        proxyFiled.set(obj, myHystrixInvocationHandler);
                        proxyFiled.setAccessible(false);
                        field.setAccessible(false);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

        return target;
    }

    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();
        return target;
    }
}
