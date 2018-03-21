package com.ch.cloud.demo.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


/**
 * @author wei9.li
 * @date 2017/4/27
 */
@ControllerAdvice
public class RestExceptionHandler {
    /**
     * 处理RestException.
     */
    @ExceptionHandler(value = {RestException.class})
    public final ResponseEntity<?> handleException(RestException ex, WebRequest request) {
        return ResponseEntity.status(ex.status).body(ex.getError());
    }

    /**
     * 处理JSR311 Validation异常.
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public final ResponseEntity<?> handleException(MethodArgumentNotValidException ex, WebRequest request) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuffer stringBuffer = new StringBuffer();
        for (ObjectError error : errors) {
            if (StringUtils.isEmpty(stringBuffer.toString())) {
                stringBuffer.append(error.getDefaultMessage());
            } else {
                stringBuffer.append("|" + error.getDefaultMessage());
            }
        }
        RestErrorResult error = new RestErrorResult();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(stringBuffer.toString());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(error);
    }
}
