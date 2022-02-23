package org.test.task.app.testcase.behavior;

import org.springframework.boot.test.context.TestComponent;
import org.test.task.app.domain.dto.InputContentDto;

import java.util.List;

@TestComponent
public class PreCondition {

    public InputContentDto createResponseBody(List<String> words, String text) {

        return InputContentDto.builder().words(words).text(text).build();

    }
}
