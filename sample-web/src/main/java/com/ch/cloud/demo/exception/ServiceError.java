package com.ch.cloud.demo.exception;

/**
 * @author yjph83
 * @date 2017/11/22
 */
public enum ServiceError {
    /**
     * 字段名重复
     */
    DUPLICATE_COLUMN(40000, 400, "字段重复"),
    /**
     * 超过最大数量
     */
    EXCEED_MAXIMUM_NUMBER(40001, 400, "数目超过上限"),
    /**
     * 参数有误
     */
    PARAMETER_ERROR(40002, 400, "参数校验失败"),
    /**
     * 对象被引用
     */
    TARGET_IN_SUED(40300, 403, "对象被引用"),
    /**
     * 不允许的操作
     */
    OPERATING_NOT_PERMIT(40301, 403, "操作被拒绝"),
    /**
     * 操作目标不存在
     */
    TARGET_NOT_FOUND(40400, 404, "目标不存在"),
    /**
     * 操作目标不存在
     */
    ASSOCIATION_OBJECT_NOT_FOUND(40401, 404, "关联对象不存在"),
    /**
     * 配置节点不存在
     */
    CONFIG_NODE_FOUND(40402, 404, "配置节点不存在"),
    /**
     * 数据库操作过期
     */
    DB_OPERATE_LOCKED(40403, 404, "数据已被更改"),
    /**
     * 删除数据失败
     */
    DB_OPERATE_FAIL(40404, 404, "数据已被更改"),
    /**
     * 数据库异常
     */
    DB_ACCESS_ERROR(50000, 500, "数据库异常"),
    /**
     * 数据库异常
     */
    IO_ACCESS_ERROR(50001, 500, "文件读写异常");

    public int code;
    public int httpStatus;
    public String message;

    ServiceError(int code, int httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
