package com.isxcode.star.plugin.config;

import com.isxcode.star.common.constant.KafkaConfigConstants;
import com.isxcode.star.common.properties.StarPluginProperties;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    private final StarPluginProperties starPluginProperties;

    public KafkaConfig(StarPluginProperties starPluginProperties) {

        this.starPluginProperties = starPluginProperties;
    }

    @Bean
    public KafkaAdmin admin() {

        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, starPluginProperties.getKafkaConfig().get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic starTopic() {

        return TopicBuilder.name(KafkaConfigConstants.DEFAULT_TOPIC_NAME)
            .partitions(Integer.parseInt(starPluginProperties.getKafkaConfig().get(KafkaConfigConstants.PARTITIONS)))
            .replicas(Integer.parseInt(starPluginProperties.getKafkaConfig().get(KafkaConfigConstants.REPLICATION_FACTOR)))
            .compact()
            .build();
    }

    @Bean
    public Map<String, Object> producerConfigs() {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, starPluginProperties.getKafkaConfig().get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {

        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {

        return new KafkaTemplate<>(producerFactory());
    }
}
