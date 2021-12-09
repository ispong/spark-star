package com.isxcode.star.common.config;

import com.isxcode.star.common.service.StarTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarAutoConfig {

    @Bean("starTemplate")
    public StarTemplate initStarTemplate() {

        return new StarTemplate();
    }
}
