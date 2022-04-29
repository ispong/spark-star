package com.isxcode.star.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties("star.client")
public class StarClientProperties {

    private Map<String, WorkerProperties> workers;
}
