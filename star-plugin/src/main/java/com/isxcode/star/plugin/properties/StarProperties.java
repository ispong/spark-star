package com.isxcode.star.plugin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "star.config")
public class StarProperties {

    private String master;

    private String hiveMetastoreUris;

    private String sparkUiPort = "30166";

    private String appName = "star spark session";
}
