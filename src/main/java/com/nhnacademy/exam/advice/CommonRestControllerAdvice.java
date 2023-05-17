package com.nhnacademy.exam.advice;

import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.UnauthorizedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 12/05/2023
 */

@RestControllerAdvice
public class CommonRestControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.initDirectFieldAccess();
    }


    @ExceptionHandler(value = {MissingServletRequestParameterException.class,HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<ErrorResponse> badRequest(Exception exception, HttpServletRequest request, HttpServletResponse response){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),HttpStatus.BAD_REQUEST.value(), request.getPathInfo());
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ErrorResponse> httpMediaTypeNotSupported(Exception exception,HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), request.getPathInfo());
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(value = {UnauthorizedUserException.class})
    public ResponseEntity<ErrorResponse> unauthorizedUser(Exception exception,HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),HttpStatus.UNAUTHORIZED.value(), request.getPathInfo());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(value = {DepartmentNotFoundException.class})
    public ResponseEntity<ErrorResponse> notfound(Exception exception, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(), request.getPathInfo());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

}
