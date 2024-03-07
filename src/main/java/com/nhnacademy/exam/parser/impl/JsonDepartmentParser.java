package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.DepartmentAndMemberDTO;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<DepartmentAndMemberDTO> parsing(File file) throws IOException {
        List<DepartmentAndMemberDTO> list = new ArrayList<>();
        list.addAll(objectMapper.readValue(file, new TypeReference<List<DepartmentAndMemberDTO>>() {
        }));

        return list;
    }
}
