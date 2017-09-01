package com.lexue.base.invokelink;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zhao.weiwei on 2017/6/10.
 */
//@Component
public class LinkRequestFilter  implements ContainerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.info("thread name is {}",Thread.currentThread().getName());
        String id = requestContext.getHeaderString("invokeLinkId");
        if(StringUtils.isEmpty(id))
            id = UUID.randomUUID().toString();
        ThreadLocalContext.setInvokeLinkId(id);
        logger.info("invokeLinkId is {}",requestContext.getHeaderString("invokeLinkId"));
        String count = requestContext.getHeaderString("invokeLinkCount");
        logger.info("invokeLinkCount is {}",count);
        if(StringUtils.isNotEmpty(count))
            ThreadLocalContext.setInvokeLinkCount(String.valueOf(Integer.parseInt(count) +1));
        else
            ThreadLocalContext.setInvokeLinkCount(String.valueOf(0));
    }
}
