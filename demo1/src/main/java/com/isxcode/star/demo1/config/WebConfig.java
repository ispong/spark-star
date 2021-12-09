package com.isxcode.star.demo1.config;

import com.isxcode.star.demo1.properties.DemoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final DemoProperties demoProperties;

    public WebConfig(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        log.info("添加外部资源");
        registry.addResourceHandler(demoProperties.getConfigPath());
    }
}
