package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.domain.DepartmentAndMemberDTO;
import com.nhnacademy.exam.formatter.CsvToDepartmentFormatter;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CsvDepartmentParser implements DepartmentParser {
    DefaultConversionService conversionService = new DefaultConversionService();

    public CsvDepartmentParser() {
        // todo: 이부분을 안하고 설정으로 넘기고 싶은데 잘 안된다.
        conversionService.addConverter(new CsvToDepartmentFormatter());
    }

    @Override
    public String getFileType() {
        return "csv";
    }

    @Override
    public List<DepartmentAndMemberDTO> parsing(File file) throws IOException {
        List<DepartmentAndMemberDTO> list = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(file))
                .withSkipLines(1)
                .build()) {

            csvReader.forEach(line ->
                    {
                        if (line.length == 4) {
                            list.add(conversionService.convert(line, DepartmentAndMemberDTO.class));
                        }
                    }
            );

        }

        return list;
    }
}
