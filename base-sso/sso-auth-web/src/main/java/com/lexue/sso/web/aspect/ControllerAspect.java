package com.lexue.sso.web.aspect;

import com.alibaba.fastjson.JSON;
import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.NotLogin;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.Log;
import com.lexue.base.domain.User;
import com.lexue.base.exception.LoginException;
import com.lexue.base.exception.PermissionException;
import com.lexue.base.util.ClassUtil;
import com.lexue.base.util.DateUtils;
import com.lexue.sso.web.feignclient.LogFeignClient;
import com.lexue.sso.web.feignclient.SsoFeignClient;
import com.lexue.sso.web.service.SsoUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Aspect
@Component
public class ControllerAspect {

    private final static Logger LOG = LoggerFactory.getLogger(ControllerAspect.class);
    @Autowired
    private SsoUserService ssoUserService;
    @Autowired
    SsoFeignClient ssoFeignClient;
    @Autowired
    LogFeignClient logFeignClient;
    @Pointcut("execution(public * com.lexue.sso.*.controller.*.*(..))")
    public void poin() {
    }

    @Before("poin()")
    public void before(JoinPoint joinpoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        Class c = ClassUtil.getClass(joinpoint);
        Method method = ClassUtil.getMethod(joinpoint);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if(velidationLogin(c,method)){
            //登录认证
            if( user == null ){
                user=ssoUserService.getUser();
                if(user == null){
                    request.getRequestDispatcher("/logout").forward(request, response);
                    throw  new LoginException();
                }
            }
            if(validationPermission(user, method)){
                //权限认证
                request.getRequestDispatcher("/401").forward(request, response);
                throw  new PermissionException();
            }
        }
        if (method.isAnnotationPresent(SystemControllerLog.class)) {
            StringBuffer requestURL = request.getRequestURL();
            //请求的IP
            String ip = getRemoteAddr(request);
            String params = "";
            if (joinpoint.getArgs() !=  null && joinpoint.getArgs().length > 0) {
                for ( int i = 0; i < joinpoint.getArgs().length; i++) {
                    params += JSON.toJSONString(joinpoint.getArgs()[i]) + ";";
                }
            }
            try {
                Log log = new Log();
                log.setId(UUID.randomUUID().toString().replace("-",""));
                log.setDescription(getControllerMethodDescription(joinpoint));
                log.setMethod((joinpoint.getTarget().getClass().getName() + "." + joinpoint.getSignature().getName() + "()"));
                log.setRequestIp(ip);
                log.setOperName(user.getLoginName());
                log.setParams(params);
                log.setUrl(requestURL.toString());
                log.setCreateDate(new Date());
                //保存数据库
                logFeignClient.addLog(log);
            }  catch (Exception e) {
                //记录本地异常日志
                LOG.error("==前置通知异常==");
                LOG.error("异常信息:{}", e.getMessage());
            }
        }
    }
    public  String getRemoteAddr(HttpServletRequest request){
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).module();
                    description += "-"+method.getAnnotation(SystemControllerLog.class).method();
                    break;
                }
            }
        }
        return description;
    }
    //判断用户是否需要登录认证 需要认证返回true 不需要认证返回false
    private boolean velidationLogin(Class c,Method method){
        //类状态,方法状态
        boolean classStatus = true,methodStatus = true;
        //类,方法是否存在注解
        boolean classHas = false,methodHas = false;
        if(c.isAnnotationPresent(NotLogin.class)){
            NotLogin notLogin = (NotLogin)c.getAnnotation(NotLogin.class);
            classStatus = notLogin.value();
            classHas = true;
        }

        if(method.isAnnotationPresent(NotLogin.class)){
            NotLogin notLogin = (NotLogin)method.getAnnotation(NotLogin.class);
            methodStatus = notLogin.value();
            methodHas = true;
        }
        //当方法注解状态为false时 或者 当方法与类上都没有notLogin注解时 或者 当类的注解为false并且方法没有notLogin注解时 满足以上条件需要登录认证
        if( methodStatus == false || ( !classHas && !methodHas) || (classStatus==false && !methodHas) ){
            return true;
        }
        return false;
    }

    //判断当前用户访问权限 有访问权限返回false 无访问权限返回true
    private boolean validationPermission(User sysUser, Method method){
        if(method.isAnnotationPresent(Permission.class)){
            Permission permission = method.getAnnotation(Permission.class);
            List<String> permissions = ssoFeignClient.getPermissionByCache(sysUser.getId()).getData();
            if(!permissions.contains(permission.value())){
                return true;
            }
        }
        return false;
    }
}
