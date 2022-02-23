package org.test.task.app.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class TestContentParserConfiguration {

    @Bean("restTemplate")
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}