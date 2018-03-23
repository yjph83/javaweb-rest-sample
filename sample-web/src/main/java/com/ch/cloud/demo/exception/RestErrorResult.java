package com.ch.cloud.demo.exception;

/**
 *
 * @author yjph83
 * @date 2017/6/22
 */
public class RestErrorResult {
    public int code;
    public String message;

    public RestErrorResult() {
    }

    public RestErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
