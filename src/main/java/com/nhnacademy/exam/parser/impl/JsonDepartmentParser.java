package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JsonDepartmentParser implements DepartmentParser {
    private final ObjectMapper objectMapper;

    @Override
    public String getFileType() {
        return "json";
    }

    @Override
    public List<DepartmentDTO> parsing(File file) throws IOException {
        List<DepartmentDTO> list = new ArrayList<>();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:data/*.json");

        for (Resource resource : resources) {
            list.addAll(
                    objectMapper.readValue(resource.getFile(), new TypeReference<List<DepartmentDTO>>() {
                    }));
        }

        return list;
    }
}
