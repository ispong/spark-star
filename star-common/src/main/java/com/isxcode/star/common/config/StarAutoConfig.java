package com.isxcode.star.common.config;

import com.isxcode.star.common.properties.StarProperties;
import com.isxcode.star.common.template.StarFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarAutoConfig {

    private final StarProperties starProperties;

    public StarAutoConfig(StarProperties starProperties) {
        this.starProperties = starProperties;
    }

    @Bean
    public StarProperties starProperties() {

        return new StarProperties();
    }

    @Bean
    public StarFactory starFactory() {

        return new StarFactory(starProperties);
    }

}
