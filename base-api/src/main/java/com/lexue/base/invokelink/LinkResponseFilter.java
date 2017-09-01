package com.lexue.base.invokelink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Created by zhao.weiwei on 2017/6/10.
 */
//@Component
public class LinkResponseFilter implements ContainerResponseFilter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        logger.info("thread name is {}",Thread.currentThread().getName());
        logger.info("invokeLinkId is {}",ThreadLocalContext.getInvokeLinkId());
        logger.info("invokeLinkCount is {}",ThreadLocalContext.getInvokeLinkCount());
    }
}
