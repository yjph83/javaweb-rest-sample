package com.ch.cloud.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * @author yjph83
 * @date 2017/4/27
 */
public class RestException extends RuntimeException {
    public int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    RestErrorResult error;

    public RestException() {
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RestErrorResult getError() {
        return error;
    }

    public void setError(RestErrorResult error) {
        this.error = error;
    }
}
