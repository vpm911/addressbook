package com.book.addressbook.aop;

import com.book.addressbook.dto.ErrorResponse;
import com.book.addressbook.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e){
        log.error("Not found exception: {}", e);
        ErrorResponse response = new ErrorResponse(e.getMessage(),e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
