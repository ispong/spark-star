package com.isxcode.star.common.config;

import com.isxcode.star.common.properties.StarClientProperties;
import com.isxcode.star.common.template.StarEventHandler;
import com.isxcode.star.common.template.StarEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableKafka
@ConditionalOnClass(StarAutoConfig.class)
public class KafkaConfig {

    @Resource
    private StarEventHandler starEventHandler;

    private final StarClientProperties starClientProperties;

    public KafkaConfig(StarClientProperties starClientProperties) {

        this.starClientProperties = starClientProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "star.node.kafka-config", name = "bootstrap.servers")
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, starClientProperties.getKafkaConfig().get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, starClientProperties.getKafkaConfig().get(ConsumerConfig.GROUP_ID_CONFIG));
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    @ConditionalOnProperty(prefix = "star.node.kafka-config", name = "bootstrap.servers")
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    @ConditionalOnProperty(prefix = "star.node.kafka-config", name = "bootstrap.servers")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean(StarEventHandler.class)
    public StarEventHandler initStarEventHandler() {

        log.debug("初始化 StarEventHandler");
        return new StarEventHandler() {
        };
    }

    @Bean
    @ConditionalOnBean(StarEventHandler.class)
    @ConditionalOnProperty(prefix = "star.node.kafka-config", name = "bootstrap.servers")
    public StarEventService initStarEventService() {

        log.debug("初始化 StarEventService");
        return new StarEventService(starEventHandler);
    }
}
