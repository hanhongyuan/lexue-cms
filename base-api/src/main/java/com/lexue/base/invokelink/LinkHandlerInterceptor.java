package com.lexue.base.invokelink;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by zhao.weiwei on 2017/6/10.
 */
@Component
public class LinkHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String id = request.getHeader("invokeLinkId");
        String count = request.getHeader("invokeLinkCount");
        if(StringUtils.isEmpty(id))
            id = UUID.randomUUID().toString();
        ThreadLocalContext.setInvokeLinkId(id);
        if(StringUtils.isNotEmpty(count))
            ThreadLocalContext.setInvokeLinkCount(String.valueOf(Integer.parseInt(count) +1));
        else
            ThreadLocalContext.setInvokeLinkCount(String.valueOf(0));
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
