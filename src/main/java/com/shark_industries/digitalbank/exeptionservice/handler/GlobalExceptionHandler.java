package com.shark_industries.digitalbank.exeptionservice.handler;

import com.shark_industries.digitalbank.exception.UserAlreadyExistsException;
import com.shark_industries.digitalbank.exeptionservice.model.ErrorResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponce> handlerUserAllreadyException(UserAlreadyExistsException exc){
        log.error("UserAllreadyExc:{} ",  exc);

        ErrorResponce responce = ErrorResponce.builder()
                .code(exc.getCode())
                .message("Ошибка юзра")
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responce);
    };



}
