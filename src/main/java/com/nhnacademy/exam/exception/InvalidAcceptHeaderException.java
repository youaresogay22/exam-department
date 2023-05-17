package com.nhnacademy.exam.exception;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 12/05/2023
 */
public class InvalidAcceptHeaderException extends RuntimeException{
    public InvalidAcceptHeaderException(){
        super("required accpet header : application/json");
    }
}
