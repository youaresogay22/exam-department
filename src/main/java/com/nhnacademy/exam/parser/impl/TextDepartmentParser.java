package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.formatter.CsvToDepartmentFormatter;
import com.nhnacademy.exam.formatter.TextToDepartmentFormatter;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {
    DefaultConversionService conversionService = new DefaultConversionService();

    public TextDepartmentParser() {
        // todo: 이부분을 안하고 설정으로 넘기고 싶은데 잘 안된다.
        conversionService.addConverter(new TextToDepartmentFormatter());
    }

    @Override
    public String getFileType() {
        return "txt";
    }

    @Override
    public List<DepartmentDTO> parsing(File file) throws IOException {
        List<DepartmentDTO> list = new ArrayList<>();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:data/*.txt");

        for (Resource resource : resources) {

            try (Stream<String> stream = Files.lines(resource.getFile().toPath())) {
                stream.forEach(line -> list.add(conversionService.convert(line, DepartmentDTO.class)));
            }
        }

        return list;
    }
}
