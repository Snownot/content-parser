package org.test.task.app.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * This class helped to collect data from input
 * and then prepared for mapping
 */
@Data
@Builder
public class ConvertDto {

    private String word;

    private String text;

}
