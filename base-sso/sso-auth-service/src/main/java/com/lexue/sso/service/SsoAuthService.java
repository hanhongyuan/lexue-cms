package com.lexue.sso.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * service-sso 启动类
 * */


@RestController
@EnableCaching
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = { "com.lexue" })
@EnableFeignClients(basePackages = "com.lexue")
@ServletComponentScan(basePackages = { "com.lexue" })
public class SsoAuthService {
	public static void main(String[] args) {
		SpringApplication.run(SsoAuthService.class, args);
	}
}
