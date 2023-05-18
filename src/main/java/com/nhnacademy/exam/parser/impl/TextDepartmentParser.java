package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.List;

@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return null;
    }

    @Override
    public List parsing(File file) throws IOException {
        return null;
    }
}
