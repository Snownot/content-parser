package org.test.task.app.domain.dto;

import lombok.Data;

/**
 * This class include word which
 * must be presented inside input text
 * and count repeats
 */
@Data
public class ResultContentDto {

    private String word;

    private Integer count;

}
