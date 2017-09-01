package com.lexue.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookieUtil {
	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

	/**
	 * 设置cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * 
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		setCookie(request, response, name, value, 0);
	}

	/**
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 *            设置cookie，设定时间
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			int maxAge) {
		if (request == null || response == null)
			return;
		Cookie cookie = new Cookie(name, value == null ? "" : value.replaceAll("\r\n", ""));
		String url = request.getRequestURL().toString();
		String domain = getCookieDomain(url);
		if (null != domain)
			cookie.setDomain(domain);
		cookie.setPath(getPath(request));
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		// 一律设置httpOnly 为 true,防止攻击
		response.addCookie(cookie);
		logger.info("setCookie domain:{},name:{},value:{},path:{},maxAge:{}", cookie.getDomain(), cookie.getName(),
				cookie.getValue(), cookie.getPath(), cookie.getMaxAge());
	}

	/**
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 *            设置cookie，设定时间
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			int maxAge, String domain) {
		if (request == null || response == null)
			return;
		Cookie cookie = new Cookie(name, value == null ? "" : value.replaceAll("\r\n", ""));
		if (null != domain && !domain.isEmpty())
			cookie.setDomain(domain);
		cookie.setPath(getPath(request));
		cookie.setMaxAge(maxAge);
		// 一律设置httpOnly 为 true,防止攻击
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		logger.info("setCookie domain:{},name:{},value:{},path:{},maxAge:{}", cookie.getDomain(), cookie.getName(),
				cookie.getValue(), cookie.getPath(), cookie.getMaxAge());
	}

	public static String getCookieDomain(String url) {
		String domain = "";
		try {
			domain = getTopDomainWithoutSubdomain(url);
		} catch (MalformedURLException e) {
			logger.warn("getCookieDomain exception", e);
		}
		if (null != domain && !"".equals(domain))
			return "." + domain;
		return null;
	}

	public static String getTopDomainWithoutSubdomain(String url) throws MalformedURLException {
		String host = new URL(url).getHost().toLowerCase();
		Pattern pattern = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(host);
		while (matcher.find())
			return matcher.group();
		return null;
	}

	private static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return (path == null || path.length() == 0) ? "/" : path;
	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookie
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			String url = request.getRequestURL().toString();
			String domain = getCookieDomain(url);
			if (null != domain)
				cookie.setDomain(domain);
			cookie.setPath(getPath(request));
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	/**
	 * 获取cookie的值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(name))
					return cookie.getValue();
		return "";
	}
}
