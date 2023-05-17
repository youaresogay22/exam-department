package com.nhnacademy.exam.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 10/05/2023
 */

@Component
@RequiredArgsConstructor
public class DepartmentParserResolver {
    private final List<DepartmentParser> departmentParserList;

    public DepartmentParser getDepartmentParser(String fileName){
        for (DepartmentParser parser : departmentParserList) {
            if(parser.matchFileType(fileName)){
                return parser;
            }
        }
        return null;
    }

}
