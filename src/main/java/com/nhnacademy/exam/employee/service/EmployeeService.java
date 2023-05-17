package com.nhnacademy.exam.employee.service;

import com.nhnacademy.exam.employee.entity.Employee;
import com.nhnacademy.exam.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void removeAll(){
        employeeRepository.deleteAll();
    }

    public List<Employee> saveAll(Collection<Employee> c){
        return employeeRepository.saveAll(c);
    }
}
