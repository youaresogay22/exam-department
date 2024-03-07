package com.nhnacademy.exam.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentParserResolver {
    private final List<DepartmentParser> departmentParserList;

    public DepartmentParser getDepartmentParser(String fileName) {
        String fileType = StringUtils.getFilenameExtension(fileName);

        for (DepartmentParser parser : departmentParserList) {
            assert fileType != null;
            if (fileType.equals(parser.getFileType()))
                return parser;

        }
        return null;
    }

}
