package com.lexue.sso.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * sso-web的启动类
 * <P>
 * 
 */
@Controller
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = { "com.lexue" })
@EnableFeignClients(basePackages = "com.lexue")
@ServletComponentScan(basePackages = { "com.lexue" })
public class SsoAuthWeb {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public ServletRegistrationBean servletRegistrationBean() throws ServletException {
		ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(),"/images/kaptcha.jpg");
		servlet.addInitParameter("kaptcha.border", "no");/*kborder*///无边框
		servlet.addInitParameter("kaptcha.session.key", "kaptcha_code");//session key
		servlet.addInitParameter("kaptcha.textproducer.font.color", "black");
		servlet.addInitParameter("kaptcha.textproducer.font.size", "25");
		servlet.addInitParameter("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
		servlet.addInitParameter("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
		servlet.addInitParameter("kaptcha.image.width", "90");
		servlet.addInitParameter("kaptcha.image.height", "33");
		servlet.addInitParameter("kaptcha.textproducer.char.length", "4");
		servlet.addInitParameter("kaptcha.textproducer.char.space", "5");
		servlet.addInitParameter("kaptcha.background.clear.from", "247,247,247"); //和登录框背景颜色一致
		servlet.addInitParameter("kaptcha.background.clear.to", "247,247,247");
		return servlet;
	}
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 设置文件大小限制 ,超出设置页面会抛出异常信息，
		// 这样在文件上传的地方就需要进行异常信息的处理了;
		//factory.setMaxFileSize("256KB"); // KB,MB
		/// 设置总上传数据总大小
		//factory.setMaxRequestSize("512KB");
		// Sets the directory location where files will be stored.
		//factory.setLocation("/");
		return factory.createMultipartConfig();
	}
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setSessionTimeout(600);//单位为S
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(SsoAuthWeb.class, args);
	}
}
