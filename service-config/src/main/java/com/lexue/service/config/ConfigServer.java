package com.lexue.service.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新配置中心的服务
 */
@RestController
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
@EnableAutoConfiguration
public class ConfigServer {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ConfigServer.class);
		ApplicationContext applicationContext = application.run(args);
		System.out.println(applicationContext.getClass());
	}
}
