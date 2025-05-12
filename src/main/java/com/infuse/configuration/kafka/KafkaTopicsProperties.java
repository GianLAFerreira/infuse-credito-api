package com.infuse.configuration.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "infuse.kafka.topics")
@Data
public class KafkaTopicsProperties {
    private String consultaNfse;
}
