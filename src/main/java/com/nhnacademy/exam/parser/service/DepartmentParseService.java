package com.nhnacademy.exam.parser.service;

import com.nhnacademy.exam.department.entity.Department;
import com.nhnacademy.exam.department.service.DepartmentService;
import com.nhnacademy.exam.employee.entity.Employee;
import com.nhnacademy.exam.employee.service.EmployeeService;
import com.nhnacademy.exam.deptmember.entity.DepartmentMember;
import com.nhnacademy.exam.deptmember.service.DeptMemberService;
import com.nhnacademy.exam.parser.dto.DepartmentInfoDto;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.department.dto.DepartmentDto;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.parser.dto.DepartmentParseResultDto;
import com.nhnacademy.exam.employee.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 10/05/2023
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class DepartmentParseService implements InitializingBean {
    private final DepartmentParserResolver departmentParserResolver;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final DeptMemberService deptMemberService;
    public DepartmentParseResultDto parse(List<File> files) throws IOException {
        List<DepartmentInfoDto> departmentInfoDtoList = new ArrayList<>();
        for (File file : files) {
            DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(file.getName());
            if(Objects.nonNull(departmentParser)){
                departmentInfoDtoList.addAll( departmentParser.parsing(file) );
            }
        }

        Set<DepartmentDto> departmentDtoSet = getDepartmentSet(departmentInfoDtoList);
        Set<EmployeeDto> employeeDtoSet = getEmploySet(departmentInfoDtoList);

        for (EmployeeDto employeeDto : employeeDtoSet) {
            log.debug("employeeDto:{}",employeeDto);
        }

        for (DepartmentDto departmentDto : departmentDtoSet) {
            log.debug("departmentDto:{}",departmentDto);
        }

        return new DepartmentParseResultDto(employeeDtoSet,departmentDtoSet);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initTables();

        //bean 생성시 실행
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:data/*");
        List<File> files = new ArrayList<>();
        for (Resource resource : resources) {
            log.debug("resource:{}", resource.getFilename());
           files.add(resource.getFile());
        }

        DepartmentParseResultDto departmentParseResultDto =  parse(files);

        //부서 등록
        List<Department> departments = departmentService.saveAll(departmentParseResultDto.getDepartmentMap().values());
        //직원 등록
        List<Employee> employees = employeeService.saveAll(departmentParseResultDto.getEmployeeMap().values());

        for (EmployeeDto employeeDto : departmentParseResultDto.getEmployeeDtoSet()) {
            Employee employee = employees.stream().filter(o->o.getEmpId().equals(employeeDto.getEmpId())).findFirst().orElse(null);
            for(String deptId : employeeDto.getDeptIdSet()) {
                Department department = departments.stream().filter(o->o.getDeptId().equals(deptId)).findFirst().orElse(null);
                deptMemberService.save(new DepartmentMember(department,employee));
            }
        }
    }

    private void initTables(){
        deptMemberService.removeAll();
        departmentService.removeAll();
        employeeService.removeAll();
    }

    private Set<EmployeeDto> getEmploySet(List<DepartmentInfoDto> departmentInfoDtoList){
        Map<String, EmployeeDto> employeeDtoMap = new HashMap<>();
        for (DepartmentInfoDto departmentInfoDto : departmentInfoDtoList) {
            EmployeeDto employeeDto;
            if(employeeDtoMap.containsKey(departmentInfoDto.getEmpId())){
                employeeDto = employeeDtoMap.get(departmentInfoDto.getEmpId());
                employeeDto.addDeptId(departmentInfoDto.getDeptId());
            }else{
                employeeDto = new EmployeeDto(departmentInfoDto.getEmpId(), departmentInfoDto.getEmpName());
                employeeDto.addDeptId(departmentInfoDto.getDeptId());
                employeeDtoMap.put(departmentInfoDto.getEmpId(),employeeDto);
            }
        }
        return new HashSet<>(employeeDtoMap.values());
    }

    private Set<DepartmentDto> getDepartmentSet(List<DepartmentInfoDto> departmentInfoDtoList){
        Set<DepartmentDto> departmentDtoSet = new HashSet<>();
        for (DepartmentInfoDto departmentInfoDto : departmentInfoDtoList) {
            departmentDtoSet.add(new DepartmentDto(departmentInfoDto.getDeptId(), departmentInfoDto.getDeptName()));
        }
        return departmentDtoSet;
    }
}
