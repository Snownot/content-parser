package org.test.task.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.test.task.app.domain.dto.ConvertDto;
import org.test.task.app.domain.dto.InputContentDto;
import org.test.task.app.domain.dto.ResultContentDto;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentParserService {

    @Resource(name = "modelMapper")
    private ModelMapper modelMapper;

    public List<ResultContentDto> parse(InputContentDto inputContentDto) {

        ArrayList<ConvertDto> convertData = new ArrayList<>();

        inputContentDto.getWords().stream()
                .filter(value -> inputContentDto.getText().contains(value))
                .forEach(word -> convertData
                        .add(ConvertDto.builder()
                        .word(word).text(inputContentDto.getText())
                        .build()));

        return convertData.stream()
                .map(value -> modelMapper.map(value, ResultContentDto.class))
                .sorted(Comparator.comparingInt(ResultContentDto::getCount).reversed())
                .collect(Collectors.toList());

    }
}