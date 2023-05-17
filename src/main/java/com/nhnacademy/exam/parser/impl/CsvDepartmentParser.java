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
public class CsvDepartmentParser implements DepartmentParser {
    private static final String FILE_TYPE = "csv";
    private static final String COL_DELIMER=",";

    @Override
    public String getFileType() {
        return FILE_TYPE;
    }

    @Override
    public List<DepartmentInfoDto> parsing(File file){
        List<DepartmentInfoDto> departmentInfoDtoList = new ArrayList<>();
        String[] lines;

        try {
            lines = FileUtils.getTextFromFile(file).split(System.lineSeparator());
        } catch (Exception e) {
            log.error("{}",e);
            throw e;
        }

        for (String line : lines) {
            if(line.trim().startsWith("사번") || line.trim().equals("")){
                continue;
            }

            String[] cols = line.split(COL_DELIMER);
            log.debug("--------------------------");
            log.debug("empId:{}", cols[0]);
            log.debug("empName:{}", cols[1]);
            log.debug("deptName:{}", cols[2]);
            log.debug("deptId:{}", cols[3]);
            log.debug("--------------------------");

            departmentInfoDtoList.add(new DepartmentInfoDto(cols[3].trim(),cols[2].trim(),cols[0].trim(),cols[1].trim()));
        }
        return departmentInfoDtoList;
    }
}
