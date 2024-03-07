package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.domain.DepartmentAndMemberDTO;
import com.nhnacademy.exam.formatter.TextToDepartmentFormatter;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {
    DefaultConversionService conversionService = new DefaultConversionService();
    private final String[] HEADER = {"사번", "이름", "부서", "부서코드"};
    private final String[] WALL = {"----------------", "----------------", "----------------", "----------------"};


    public TextDepartmentParser() {
        // todo: 이부분을 안하고 설정으로 넘기고 싶은데 잘 안된다.
        conversionService.addConverter(new TextToDepartmentFormatter());
    }

    @Override
    public String getFileType() {
        return "txt";
    }

    @Override
    public List<DepartmentAndMemberDTO> parsing(File file) throws IOException {
        List<DepartmentAndMemberDTO> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(line ->
                    {
                        String[] srcArray = preProcess(line);
                        if (!Arrays.equals(srcArray, HEADER) && !Arrays.equals(srcArray, WALL)) {
                            list.add(conversionService.convert(srcArray, DepartmentAndMemberDTO.class));
                        }
                    }
            );
        }
        return list;
    }

    private String[] preProcess(String source) {
        String sb = new StringBuilder(source)
                .deleteCharAt(0)
                .deleteCharAt(source.length() - 2)
                .toString();

        String[] test2 = StringUtils.trimAllWhitespace(sb).split("\\|");

        return test2;
    }
}
