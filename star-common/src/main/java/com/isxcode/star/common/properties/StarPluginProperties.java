package com.isxcode.star.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "star.plugin")
public class StarPluginProperties {

    /**
     * 默认application name
     */
    private String appName = "spark star application";

    /**
     * 默认的配置 使用本地模式
     */
    private String master = "local";

    /**
     * 额外的配置
     */
    private Map<String, String> sparkConfig;

    /**
     * 服务器密钥
     */
    private String serverKey = "star-key";

    /**
     * 相关的kafka配置
     */
    private Map<String, String> kafkaConfig;
}
