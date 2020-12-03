package com.xslgy.common.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "new-ideas")
public class ParamConfig {

    /**
     * cdn地址
     */
    private String cdnUrl;
}
