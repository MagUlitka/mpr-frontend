package com.example.mprprojectmvn.exceptionhandler;


public class InvalidStudentNameException extends RuntimeException {
    public InvalidStudentNameException(String s) {
        super(s);
    }
}
