package com.nhnacademy.exam.parser.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.parser.dto.DepartmentInfoDto;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 10/05/2023
 */

@RequiredArgsConstructor
@Component
@Slf4j
public class JsonDepartmentParser implements DepartmentParser {
    private static final String FILE_TYPE = "json";

    private final ObjectMapper objectMapper;

    @Override
    public String getFileType() {
        return FILE_TYPE;
    }

    @Override
    public List<DepartmentInfoDto> parsing(File file) throws IOException {
        List<DepartmentInfoDto> departmentInfoDtoList;

        try {
           departmentInfoDtoList = objectMapper.readValue(file, new TypeReference<List<DepartmentInfoDto>>() {});
        } catch (Exception e) {
            log.error("{}",e);
            throw e;
        }
        return departmentInfoDtoList;
    }
}
