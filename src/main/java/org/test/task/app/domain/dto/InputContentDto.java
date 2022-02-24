package org.test.task.app.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * This class include list of words which
 * can be presented inside text
 */
@Data
@Builder
public class InputContentDto {

    private List<String> words;

    private String text;

}
