package com.ch.cloud.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * @author yjph83
 * @date 2017/4/27
 */
public class RestException extends RuntimeException {
    public int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    Object error;

    public RestException() {}

    public RestException(int status) {
        this.status = status;
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(int status, String message) {
        super(message);
        this.status = status;
        this.error = new RestErrorResult(this.status, message);
    }

    public RestException(int status, String code, String message, String description) {
        super(message);
        this.status = status;
        this.error = new RestErrorResultV2(code, message, description);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
