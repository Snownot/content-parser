package org.test.task.app.configuration;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.task.app.domain.dto.ConvertDto;
import org.test.task.app.domain.dto.ResultContentDto;

@Configuration
public class ModelMapperConfiguration {

    @Bean("modelMapper")
    public ModelMapper createModelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        Converter<ConvertDto, Integer> converter = context -> convert(context.getSource());

        modelMapper.typeMap(ConvertDto.class, ResultContentDto.class)
                .addMapping(ConvertDto::getWord, ResultContentDto::setWord)
                .addMappings(mapper -> mapper.using(converter)
                        .map(source -> source, ResultContentDto::setCount));

        return modelMapper;

    }

    private Integer convert(ConvertDto convert) {

        return StringUtils.countMatches(convert.getText(), convert.getWord());

    }
}