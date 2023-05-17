package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.parser.dto.DepartmentInfoDto;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 10/05/2023
 */
@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {
    private static final String FILE_TYPE="txt";

    private static final String COL_DELIMER="\\|";


    @Override
    public String getFileType() {
        return FILE_TYPE;
    }

    @Override
    public List<DepartmentInfoDto> parsing(File file) {
        List<DepartmentInfoDto> departmentInfoDtoList = new ArrayList<>();
        String[] lines;

        try {
            lines = FileUtils.getTextFromFile(file).split(System.lineSeparator());
        } catch (Exception e) {
            log.error("{}",e);
            throw e;
        }

        for (String line : lines) {
            if(line.trim().startsWith("|-") || line.trim().startsWith("|사번") ){
                continue;
            }
            String[] cols = line.split(COL_DELIMER);
            log.debug("--------------------------");
            log.debug("empId:{}", cols[1]);
            log.debug("empName:{}", cols[2]);
            log.debug("deptName:{}", cols[3]);
            log.debug("deptId:{}", cols[4]);
            log.debug("--------------------------");

            departmentInfoDtoList.add(new DepartmentInfoDto(cols[4].trim(),cols[3].trim(),cols[1].trim(),cols[2].trim()));
        }
        return departmentInfoDtoList;
    }

}
