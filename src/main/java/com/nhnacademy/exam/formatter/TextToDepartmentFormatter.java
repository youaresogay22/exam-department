package com.nhnacademy.exam.formatter;

import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.exception.DataHeaderException;
import com.nhnacademy.exam.exception.DataNotCompatibleException;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

public class TextToDepartmentFormatter implements Converter<String, DepartmentDTO> {
    private final String[] HEADER = {"사번", "이름", "부서", "부서코드"};

    @Override
    public DepartmentDTO convert(@NonNull String source) {
        DepartmentDTO department = new DepartmentDTO();

        String[] srcArray = StringUtils.split(StringUtils.trimAllWhitespace(source), "|");

        if (srcArray == null || srcArray.length != 4)
            throw new DataNotCompatibleException();

        else if (Arrays.equals(srcArray, HEADER)) {
            throw new DataHeaderException();

        } else {
            department.setId(srcArray[0]);
            department.setName(srcArray[1]);
            department.setDepartment(srcArray[2]);
            department.setDepartmentCode(srcArray[3]);
        }

        return department;
    }
}
