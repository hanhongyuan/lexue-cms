package com.lexue.sso.web.webconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * author lilong
 */
@Data
@Component
@ConfigurationProperties(prefix = "lexue.application")
public class WebConfig {

    private String name;
    private String client;
    private String dnsurl;
    private String key;
    private String secret;
    private String bucket;
    private String video;
    private String picture;
    private String endpoint;

}