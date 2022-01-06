package com.isxcode.star.demo1.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "star.plugin")
public class DemoProperties {

    private String appName;

    private String master;

    private Map<String, String> sparkConfig;
}
