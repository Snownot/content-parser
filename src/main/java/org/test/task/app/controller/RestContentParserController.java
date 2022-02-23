package org.test.task.app.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.task.app.domain.dto.ResultContentDto;
import org.test.task.app.domain.dto.InputContentDto;
import org.test.task.app.service.ContentParserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("parser")
public class RestContentParserController {

    @Resource
    private ContentParserService contentParserService;

    @RequestMapping(path = "all", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ResultContentDto>> getParserResultAll(@RequestBody InputContentDto inputContentDto) {

        return ResponseEntity.ok().body(contentParserService.parse(inputContentDto));

    }

}