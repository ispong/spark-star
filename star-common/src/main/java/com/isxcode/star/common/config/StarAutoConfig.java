package com.isxcode.star.common.config;

import com.isxcode.star.common.properties.StarProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarAutoConfig {

    @Bean("starProperties")
    public StarProperties starProperties() {

        return new StarProperties();
    }
}
