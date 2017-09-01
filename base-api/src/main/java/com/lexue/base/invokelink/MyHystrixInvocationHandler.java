

/*
                            _ooOoo_
                           o8888888o
                           88" . "88
                           (| -_- |)
                            O\ = /O
                        ____/`---'\____
                      .   ' \\| |// `.
                       / \\||| : |||// \
                     / _||||| -:- |||||- \
                       | | \\\ - /// | |
                     | \_| ''\---/'' | |
                      \ .-\__ `-` ___/-. /
                   ___`. .' /--.--\ `. . __
                ."" '< `.___\_<|>_/___.' >'"".
               | | : `- \`.;`\ _ /`;.`/ - ` : | |
                 \ \ `-. \_ __\ /__ _/ .-` / /
         ======`-.____`-.___\_____/___.-`____.-'======
                            `=---='

         .............................................
                  佛祖镇楼                  BUG辟易
          佛曰:
                  写字楼里写字间，写字间里程序员；
                  程序人员写程序，又拿程序换酒钱。
                  酒醒只在网上坐，酒醉还来网下眠；
                  酒醉酒醒日复日，网上网下年复年。
                  但愿老死电脑间，不愿鞠躬老板前；
                  奔驰宝马贵者趣，公交自行程序员。
                  别人笑我忒疯癫，我笑自己命太贱；
                  不见满街漂亮妹，哪个归得程序员？
*/
package com.lexue.base.invokelink;

import com.netflix.hystrix.HystrixCommand;
import feign.InvocationHandlerFactory;
import feign.MethodMetadata;
import feign.Target;
import feign.hystrix.FallbackFactory;
import feign.hystrix.SetterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Completable;
import rx.Observable;
import rx.Single;

import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * MyHystrixInvocationHandler自定义，每次请求生成header的绑定
 * <p>
 * Created by zhao.weiwei
 * Created on 2017/6/9 18:52
 * Email is zhao.weiwei@jyall.com
 * Copyright is 金色家园网络科技有限公司
 */
public class MyHystrixInvocationHandler implements InvocationHandler {
    private Logger LOGGER  = LoggerFactory.getLogger(getClass());
    private Target<?> target;
    private Map<Method, InvocationHandlerFactory.MethodHandler> dispatch;
    private FallbackFactory<?> fallbackFactory; // Nullable
    private Map<Method, Method> fallbackMethodMap;
    private Map<Method, HystrixCommand.Setter> setterMethodMap;

    public MyHystrixInvocationHandler(Object obj) {
        try {
            Field field = obj.getClass().getDeclaredField("target");
            field.setAccessible(true);
            this.target = (Target<?>) field.get(obj);
            field = obj.getClass().getDeclaredField("dispatch");
            field.setAccessible(true);
            this.dispatch = (Map) field.get(obj);
            if(!obj.getClass().getSimpleName().contains("FeignInvocationHandler")) {
                field = obj.getClass().getDeclaredField("fallbackFactory");
                field.setAccessible(true);
                this.fallbackFactory = (FallbackFactory<?>) field.get(obj);

                field = obj.getClass().getDeclaredField("fallbackMethodMap");
                field.setAccessible(true);
                this.fallbackMethodMap = (Map) field.get(obj);

                field = obj.getClass().getDeclaredField("setterMethodMap");
                field.setAccessible(true);
                this.setterMethodMap = (Map) field.get(obj);
            }
        } catch (Exception e) {
            LOGGER.debug("new construct error ",e);
        }
    }

    /**
     * If the method param of InvocationHandler.invoke is not accessible, i.e in a package-private
     * interface, the fallback call in hystrix command will fail cause of access restrictions. But
     * methods in dispatch are copied methods. So setting access to dispatch method doesn't take
     * effect to the method in InvocationHandler.invoke. Use map to store a copy of method to invoke
     * the fallback to bypass this and reducing the count of reflection calls.
     *
     * @return cached methods map for fallback invoking
     */
    static Map<Method, Method> toFallbackMethod(Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
        Map<Method, Method> result = new LinkedHashMap<Method, Method>();
        for (Method method : dispatch.keySet()) {
            method.setAccessible(true);
            result.put(method, method);
        }
        return result;
    }

    /**
     * Process all methods in the target so that appropriate setters are created.
     */
    static Map<Method, HystrixCommand.Setter> toSetters(SetterFactory setterFactory, Target<?> target,
                                                        Set<Method> methods) {
        Map<Method, HystrixCommand.Setter> result = new LinkedHashMap<Method, HystrixCommand.Setter>();
        for (Method method : methods) {
            method.setAccessible(true);
            result.put(method, setterFactory.create(target, method));
        }
        return result;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if ("equals".equals(method.getName())) {
            try {
                Object otherHandler =
                        args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                return equals(otherHandler);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else if ("hashCode".equals(method.getName())) {
            return hashCode();
        } else if ("toString".equals(method.getName())) {
            return toString();
        }

        InvocationHandlerFactory.MethodHandler handler = MyHystrixInvocationHandler.this.dispatch.get(method);
        InvocationHandlerFactory.MethodHandler newHander = copyHandler(handler);
        if(newHander!=null) {
            //替换handler
            if (setterMethodMap != null) {
                HystrixCommand<Object> hystrixCommand = new MyHystrixCommand(setterMethodMap.get(method), method, newHander, args);
                if (isReturnsHystrixCommand(method)) {
                    return hystrixCommand;
                } else if (isReturnsObservable(method)) {
                    // Create a cold Observable
                    return hystrixCommand.toObservable();
                } else if (isReturnsSingle(method)) {
                    // Create a cold Observable as a Single
                    return hystrixCommand.toObservable().toSingle();
                } else if (isReturnsCompletable(method)) {
                    return hystrixCommand.toObservable().toCompletable();
                }
                return hystrixCommand.execute();
            } else {
                return newHander.invoke(args);
            }
        }else
            return handler.invoke(args);
    }

    private boolean isReturnsCompletable(Method method) {
        return Completable.class.isAssignableFrom(method.getReturnType());
    }

    private boolean isReturnsHystrixCommand(Method method) {
        return HystrixCommand.class.isAssignableFrom(method.getReturnType());
    }

    private boolean isReturnsObservable(Method method) {
        return Observable.class.isAssignableFrom(method.getReturnType());
    }

    private boolean isReturnsSingle(Method method) {
        return Single.class.isAssignableFrom(method.getReturnType());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyHystrixInvocationHandler) {
            MyHystrixInvocationHandler other = (MyHystrixInvocationHandler) obj;
            return target.equals(other.target);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return target.hashCode();
    }

    @Override
    public String toString() {
        return target.toString();
    }

    public InvocationHandlerFactory.MethodHandler copyHandler(InvocationHandlerFactory.MethodHandler oldHandler) throws Exception {
        Class<?> clazz = Class.forName("feign.SynchronousMethodHandler");

        Field target = clazz.getDeclaredField("target");
        target.setAccessible(true);
        Field client = clazz.getDeclaredField("client");
        client.setAccessible(true);
        Field retryer = clazz.getDeclaredField("retryer");
        retryer.setAccessible(true);
        Field requestInterceptors = clazz.getDeclaredField("requestInterceptors");
        requestInterceptors.setAccessible(true);

        Field logger = clazz.getDeclaredField("logger");
        logger.setAccessible(true);

        Field logLevel = clazz.getDeclaredField("logLevel");
        logLevel.setAccessible(true);

        Field metadata = clazz.getDeclaredField("metadata");
        metadata.setAccessible(true);

        Field buildTemplateFromArgs = clazz.getDeclaredField("buildTemplateFromArgs");
        buildTemplateFromArgs.setAccessible(true);

        Field options = clazz.getDeclaredField("options");
        options.setAccessible(true);

        Field errorDecoder = clazz.getDeclaredField("errorDecoder");
        errorDecoder.setAccessible(true);

        Field decoder = clazz.getDeclaredField("decoder");
        decoder.setAccessible(true);

        Field decode404 = clazz.getDeclaredField("decode404");
        decode404.setAccessible(true);

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for(Constructor<?> constructor:constructors) {
            try{
                constructor.setAccessible(true);
                Object obj = constructor.newInstance(
                        target.get(oldHandler),
                        client.get(oldHandler),
                        retryer.get(oldHandler),
                        requestInterceptors.get(oldHandler),
                        logger.get(oldHandler),
                        logLevel.get(oldHandler),
                        metadata.get(oldHandler),
                        buildTemplateFromArgs.get(oldHandler),
                        options.get(oldHandler),
                        decoder.get(oldHandler),
                        errorDecoder.get(oldHandler),
                        decode404.get(oldHandler));
                InvocationHandlerFactory.MethodHandler nHander = InvocationHandlerFactory.MethodHandler.class.cast(obj);
                Field metadataField = clazz.getDeclaredField("metadata");
                metadataField.setAccessible(true);
                MethodMetadata metadataN = (MethodMetadata) metadataField.get(nHander);
                LOGGER.info("add the header invokeLinkId");
                metadataN.template().header("invokeLinkId", ThreadLocalContext.getInvokeLinkId());
                LOGGER.info("add the header invokeLinkCount");
                metadataN.template().header("invokeLinkCount", ThreadLocalContext.getInvokeLinkCount());
                return nHander;
            }catch (Exception e){
                LOGGER.debug("reflect error",e);
            }
        }
        return null;
    }

    private class MyHystrixCommand extends HystrixCommand<Object> {
        private Method method;
        private InvocationHandlerFactory.MethodHandler handler;
        private Object[] args;

        public MyHystrixCommand(Setter setter, Method method, InvocationHandlerFactory.MethodHandler handler, Object[] args) {
            super(setter);
            this.method = method;
            this.handler = handler;
            this.args = args;
        }

        @Override
        protected Object run() throws Exception {
            try {
                return this.handler.invoke(args);
            } catch (Exception e) {
                throw e;
            } catch (Throwable t) {
                throw (Error) t;
            }
        }

        @Override
        protected Object getFallback() {
            if (fallbackFactory == null) {
                return super.getFallback();
            }
            try {
                Object fallback = fallbackFactory.create(getFailedExecutionException());
                Object result = fallbackMethodMap.get(method).invoke(fallback, args);
                if (isReturnsHystrixCommand(method)) {
                    return ((HystrixCommand) result).execute();
                } else if (isReturnsObservable(method)) {
                    // Create a cold Observable
                    return ((Observable) result).toBlocking().first();
                } else if (isReturnsSingle(method)) {
                    // Create a cold Observable as a Single
                    return ((Single) result).toObservable().toBlocking().first();
                } else if (isReturnsCompletable(method)) {
                    ((Completable) result).await();
                    return null;
                } else {
                    return result;
                }
            } catch (IllegalAccessException e) {
                // shouldn't happen as method is public due to being an interface
                throw new AssertionError(e);
            } catch (InvocationTargetException e) {
                // Exceptions on fallback are tossed by Hystrix
                throw new AssertionError(e.getCause());
            }
        }
    }
}