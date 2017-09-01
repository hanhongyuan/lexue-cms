package com.lexue.base.feign;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feignClient上使用的配置类
 * <P>
 * @author   wolfking@赵伟伟
 * @mail     zww199009@163.com
 * @创作日期 2017年4月18日下午1:46:20
 * @版权     归wolfking所有
 */
//@Configuration
public class JerseyFeignConfig {

	@Bean
	public Contract feignContract() {
		return new JerseyContract();
	}
}
