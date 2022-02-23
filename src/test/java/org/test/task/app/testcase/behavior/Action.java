package org.test.task.app.testcase.behavior;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.test.task.app.domain.dto.InputContentDto;
import org.test.task.app.domain.dto.ResultContentDto;

import javax.annotation.Resource;

@TestComponent
public class Action {

    @Value("${test.requestUri}")
    private String requestUri;

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    public ResponseEntity<ResultContentDto[]> requestExecute(InputContentDto responseBody) {

        try {

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUri);

            return restTemplate.postForEntity(builder.toUriString(), responseBody, ResultContentDto[].class);

        } catch (Exception ex) {
            Assert.assertThrows(Exception.class,
                    () -> System.out.println(ex.getMessage()));
            throw ex;
        }
    }
}