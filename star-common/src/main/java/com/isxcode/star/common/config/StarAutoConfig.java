package com.isxcode.star.common.config;

import com.isxcode.star.common.properties.StarClientProperties;
import com.isxcode.star.common.properties.StarPluginProperties;
import com.isxcode.star.common.template.StarTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({StarPluginProperties.class, StarClientProperties.class})
public class StarAutoConfig {

    /**
     * 初始化用户默认配置的节点信息
     *
     * @param starClientProperties 结点配置信息
     * @return starTemplate
     */
    @Bean("starTemplate")
    public StarTemplate starTemplate(StarClientProperties starClientProperties) {

        return new StarTemplate(starClientProperties);
    }
}
