package com.nhnacademy.exam.formatter;

import com.nhnacademy.exam.domain.DepartmentAndMemberDTO;
import com.nhnacademy.exam.exception.DataNotCompatibleException;
import org.springframework.core.convert.converter.Converter;

public class TextToDepartmentFormatter implements Converter<String[], DepartmentAndMemberDTO> {


    @Override
    public DepartmentAndMemberDTO convert(String[] source) {
        DepartmentAndMemberDTO department = new DepartmentAndMemberDTO();

        if (source.length != 4)
            throw new DataNotCompatibleException();

        department.setId(source[0]);
        department.setName(source[1]);
        department.setDepartment(source[2]);
        department.setDepartmentCode(source[3]);

        return department;
    }
}
