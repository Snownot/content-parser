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

    /**
     * Instantiate an instance of the given class, and using to map types
     * wth deep mapping
     *
     * @return Created instance of a ModelMapper class
     */
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

    /**
     * Instantiate an instance of the given class, and using to map types
     * wth deep mapping
     *
     * @param convert The model contain from word which can be
     *                present inside text
     *
     * @return Count words inside text
     */
    private Integer convert(ConvertDto convert) {

        return StringUtils.countMatches(convert.getText(), convert.getWord());

    }
}