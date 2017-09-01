package com.lexue.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 服务网关
 * 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GateServer {
	public static void main(String args[]) {
		SpringApplication.run(GateServer.class, args);
	}
}
