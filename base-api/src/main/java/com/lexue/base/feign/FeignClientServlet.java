package com.lexue.base.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成feign调用的servlet
 * <P>
 * 
 * @author wolfking@赵伟伟
 * @mail zww199009@163.com
 * @创作日期 2017年4月18日下午1:42:55
 * @版权 归wolfking所有
 */
//@WebServlet(value = "/api", name = "api")
public class FeignClientServlet extends HttpServlet {

	private static final long serialVersionUID = 8189039516331722747L;
	private static final Logger logger = LoggerFactory.getLogger(FeignClientServlet.class);
	@Value("${spring.application.name:}")
	private String serviceId;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			FeignClientBuiler builder = new FeignClientBuiler();
			resp.getWriter().write(builder.applicationPath("").serviceId(serviceId).build());
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
