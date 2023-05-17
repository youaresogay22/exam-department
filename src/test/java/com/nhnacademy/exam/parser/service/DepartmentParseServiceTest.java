package com.nhnacademy.exam.parser.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.parser.impl.CsvDepartmentParser;
import com.nhnacademy.exam.parser.impl.JsonDepartmentParser;
import com.nhnacademy.exam.parser.impl.TextDepartmentParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 15/05/2023
 */
class DepartmentParseServiceTest {

    static DepartmentParserResolver departmentParserResolver;
    static TextDepartmentParser textDepartmentParser = new TextDepartmentParser();
    static CsvDepartmentParser csvDepartmentParser = new CsvDepartmentParser();
    static JsonDepartmentParser jsonDepartmentParser = new JsonDepartmentParser(new ObjectMapper());

    @BeforeAll
    static void beforeAll(){
        departmentParserResolver = new DepartmentParserResolver(List.of(textDepartmentParser,jsonDepartmentParser,csvDepartmentParser));
    }

    @Test
    @DisplayName("get-TextDepartmentParser")
    void getDepartmentParserFromText() {
        String fileName="department-1.txt";
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(fileName);
        Assertions.assertThat(departmentParser).isInstanceOf(TextDepartmentParser.class);
    }

    @Test
    @DisplayName("get-JsonDepartmentParser")
    void getDepartmentParserFromJson() {
        String fileName="department.json";
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(fileName);
        Assertions.assertThat(departmentParser).isInstanceOf(JsonDepartmentParser.class);
    }

    @Test
    @DisplayName("get-CsvDepartmentParser")
    void getDepartmentParserFromCsv() {
        String fileName="department.csv";
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(fileName);
        Assertions.assertThat(departmentParser).isInstanceOf(CsvDepartmentParser.class);
    }

    @Test
    @DisplayName("get-XmlDepartmentParser,return:null")
    void getDepartmentParserFromXml() {
        String fileName="department.xml";
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(fileName);
        Assertions.assertThat(departmentParser).isNull();
    }


    @ParameterizedTest
    @MethodSource("fileListParam")
    @DisplayName("paring-files")
    void parsing(String filePath, int size) throws IOException {

        Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:" + filePath);

        List<?> result = departmentParserResolver
                .getDepartmentParser(filePath)
                .parsing(resource.getFile());

        Assertions.assertThat(result.size()).isEqualTo(size);
    }


    private static Stream<Arguments> fileListParam(){
        return Stream.of(
                Arguments.of("data/department-1.txt",6),
                Arguments.of("data/department-2.txt",10),
                Arguments.of("data/department.csv",10),
                Arguments.of("data/department.json",10)
        );
    }

}