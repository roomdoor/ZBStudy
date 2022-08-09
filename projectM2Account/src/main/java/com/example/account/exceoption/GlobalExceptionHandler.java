package com.example.account.exceoption;

import com.example.account.dto.ErrorResponse;
import com.example.account.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.account.type.ErrorCode.*;
import static com.example.account.type.ErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ErrorResponse handlerAccountException(AccountException e) {
        log.error("{} is occurred.", e.getErrorCode());

        return ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getErrorMessage())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException is occurred.", e);

        return ErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getDescription())
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handlerDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException is occurred.", e);

        return ErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getDescription())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handlerException(Exception e) {
        log.error("Exception is occurred.", e);

        return ErrorResponse.builder()
                .errorCode(INTERNAL_SEVER_ERROR)
                .errorMessage(INTERNAL_SEVER_ERROR.getDescription())
                .build();
    }
}
