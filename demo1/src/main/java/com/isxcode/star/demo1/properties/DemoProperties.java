package com.isxcode.star.demo1.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "star.hadoop")
public class DemoProperties {

    private String ConfigPath;
}
