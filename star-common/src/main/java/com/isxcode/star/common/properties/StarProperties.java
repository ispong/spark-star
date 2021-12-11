package com.isxcode.star.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "star.config")
public class StarProperties {

    private Map<String, ServerInfo> nodes;

    private String master;

    private String hiveMetastoreUris;

    private String sparkUiPort = "30166";

    private String appName = "star spark session";

    private String privateKey;
}
