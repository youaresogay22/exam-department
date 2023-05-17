package com.nhnacademy.exam.exception;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 16/05/2023
 */
public class DepartmentNotFoundException  extends RuntimeException{
    public DepartmentNotFoundException(String deptId){
        super("department not found : " + deptId );
    }
}
