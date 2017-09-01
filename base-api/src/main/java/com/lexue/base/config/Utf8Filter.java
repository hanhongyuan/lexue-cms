/**
 * 
 */
package com.lexue.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.annotation.WebFilter;

/**
 * web - UTF-8字符编码的filter
 * <P>
 * 
 */
@WebFilter(urlPatterns = "/*", filterName = "utf-8-filter")
public class Utf8Filter extends CharacterEncodingFilter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Utf8Filter() {
		logger.info("filter set the utf-8 encoding");
		super.setEncoding("UTF-8");
		logger.info("filter forceEncoding true");
		super.setForceEncoding(true);
	}
}
