package com.lexue.sso.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @Copyright: Copyright (c) 2016,${year},
 * @Description: ${todo}
 */

@Configuration
public class ScheduleConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * config Scheduler
     *
     * @return
     */
    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean() {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setQuartzProperties(quartzProperties());
        factoryBean.setDataSource(dataSource);
        //factoryBean.setApplicationContextSchedulerContextKey("");
        return factoryBean;
    }

    public Properties quartzProperties(){
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("classpath:quartz.properties"));
        try {
            propertiesFactoryBean.afterPropertiesSet();
            return propertiesFactoryBean.getObject();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return null;
    }
}
