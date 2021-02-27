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
    /**
     * 文件路径
     */
    private String filePath;

    public String addCdnUrl(String str) {
        return cdnUrl + str;
    }
    public String removeCdnUrl(String str) {
        return str.replace(cdnUrl, "");
    }
}
