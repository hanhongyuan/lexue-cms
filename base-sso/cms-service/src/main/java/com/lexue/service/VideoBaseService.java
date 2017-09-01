package com.lexue.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * service-sso 启动类
 * */


@EnableCaching
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = { "com.lexue" })
@EnableFeignClients(basePackages = "com.lexue")
@ServletComponentScan(basePackages = { "com.lexue" })
public class VideoBaseService {
	public static void main(String[] args) {
		SpringApplication.run(VideoBaseService.class, args);
	}
}
