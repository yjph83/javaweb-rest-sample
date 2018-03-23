package com.ch.cloud.demo.exception;

/**
 * @author yjph83
 * @date 2017/6/22
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 2773237632473740766L;
    public ServiceError serviceError;
    public Object target;

    public ServiceException(ServiceError serviceError,Object target) {
        super(serviceError.message);
        this.serviceError = serviceError;
        this.target = target;
    }

}
