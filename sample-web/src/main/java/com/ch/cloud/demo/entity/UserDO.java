package com.ch.cloud.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 描述:
 * 时间:2018-03-06 8:22
 *
 * @author:yjph83
 */
public class UserDO extends AbstractDO {

    private static final long serialVersionUID = -1036445469085072873L;
    private String name;
    private Integer age;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
