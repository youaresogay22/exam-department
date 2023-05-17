package com.nhnacademy.exam.exception;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 15/05/2023
 */
public class ReadTextFileIoException extends RuntimeException  {
    public ReadTextFileIoException(String filepath){
        super("File IOException : " + filepath );
    }
}
