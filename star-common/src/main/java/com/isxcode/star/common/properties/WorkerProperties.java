package com.isxcode.star.common.properties;

import lombok.Data;

import java.util.Map;

@Data
public class WorkerProperties {

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
