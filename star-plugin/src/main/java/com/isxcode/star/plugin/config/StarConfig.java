package com.isxcode.star.plugin.config;

import com.isxcode.star.plugin.response.SuccessResponseAdvice;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarConfig {

    @Bean
    public SuccessResponseAdvice initSuccessResponseAdvice(MessageSource messageSource) {

        return new SuccessResponseAdvice(messageSource);
    }
}
