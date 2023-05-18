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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
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
        String fileName="department.txt";
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
}
