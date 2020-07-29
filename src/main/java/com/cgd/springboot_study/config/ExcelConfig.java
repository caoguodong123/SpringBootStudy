package com.cgd.springboot_study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cgd
 * @Description: 读取*.properties文件到map
 * @Date: Created in 9:44 2020/7/29
 */
@Configuration
@ConfigurationProperties(prefix = "data",ignoreUnknownFields = false)
@PropertySource("classpath:template/test.properties")
@Component
public class ExcelConfig {
    private Map<String,String> dailyTransactionDetails = new HashMap<>();

    public Map<String, String> getDailyTransactionDetails() {
        return dailyTransactionDetails;
    }

    public void setDailyTransactionDetails(Map<String, String> dailyTransactionDetails) {
        this.dailyTransactionDetails = dailyTransactionDetails;
    }
}
