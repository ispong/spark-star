package com.isxcode.star.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties("star.node")
public class StarNodeProperties {

    /**
     * 插件服务器ip
     */
    private String host;

    /**
     * 插件服务器端口
     */
    private int port;

    /**
     * 插件服务器密钥
     */
    private String key;

    /**
     * kafka topic相干配置
     */
    private Map<String, String> kafkaConfig;
}
