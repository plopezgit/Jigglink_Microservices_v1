package com.jigglink.student.service.controller;

import com.jigglink.student.service.model.exception.EmptyItineraryListException;
import com.jigglink.student.service.model.exception.StudentNotFoundException;
import com.jigglink.student.service.model.exception.UsernameAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.rmi.ServerException;
import java.util.Date;

@SuppressWarnings("unused")
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> studentNotFoundExceptionHandler(StudentNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .responseCode(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .messageDescription(request.getDescription(false))
                .responseTimeStamp(new Date())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
    public ResponseEntity<ResponseMessage> usernameAlreadyExistExceptionHandler(UsernameAlreadyExistException exception, WebRequest request) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .responseCode(HttpStatus.ALREADY_REPORTED.value())
                .message(exception.getMessage())
                .messageDescription(request.getDescription(false))
                .responseTimeStamp(new Date())
                .build(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(EmptyItineraryListException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMessage> emptyItineraryListExceptionHandler(EmptyItineraryListException exception, WebRequest request) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .responseCode(HttpStatus.NO_CONTENT.value())
                .message(exception.getMessage())
                .messageDescription(request.getDescription(false))
                .responseTimeStamp(new Date())
                .build(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException exception, WebRequest request) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .responseCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .messageDescription(request.getDescription(false))
                .responseTimeStamp(new Date())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception, WebRequest request) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .responseCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .messageDescription(request.getDescription(false))
                .responseTimeStamp(new Date())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseMessage> serverExceptionHandler(Exception exception, WebRequest request) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .messageDescription(request.getDescription(false))
                .responseTimeStamp(new Date())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
