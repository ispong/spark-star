package com.isxcode.star.common.config;

import com.isxcode.star.common.properties.StarNodeProperties;
import com.isxcode.star.common.properties.StarPluginProperties;
import com.isxcode.star.common.template.StarTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({StarPluginProperties.class, StarNodeProperties.class})
public class StarAutoConfig {

    /**
     * 初始化用户默认配置的节点信息
     *
     * @param starNodeProperties 结点配置信息
     * @return starTemplate
     */
    @Bean("starTemplate")
    public StarTemplate starTemplate(StarNodeProperties starNodeProperties) {

        return new StarTemplate(starNodeProperties);
    }
}
