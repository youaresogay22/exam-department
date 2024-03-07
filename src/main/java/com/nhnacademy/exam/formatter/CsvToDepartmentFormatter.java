package com.nhnacademy.exam.formatter;

import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.exception.DataNotCompatibleException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class CsvToDepartmentFormatter implements Converter<String[], DepartmentDTO> {
    @Override
    public DepartmentDTO convert(String[] source) {
        DepartmentDTO department = new DepartmentDTO();

        if (source.length != 4)
            throw new DataNotCompatibleException();

        department.setId(StringUtils.trimAllWhitespace(source[0]));
        department.setName(StringUtils.trimAllWhitespace(source[1]));
        department.setDepartment(StringUtils.trimAllWhitespace(source[2]));
        department.setDepartmentCode(StringUtils.trimAllWhitespace(source[3]));

        return department;
    }
}
