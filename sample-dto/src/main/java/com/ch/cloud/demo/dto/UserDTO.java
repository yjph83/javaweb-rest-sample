package com.ch.cloud.demo.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author huwei
 * @date 2018/3/7
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -2113474629420147863L;
    protected Long id;
    @NotNull(message = "姓名不能为空")
    @Size(min = 2, max = 30, message = "姓名不能小于2个字符且不能大于30个字符")
    private String name;
    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;
    @Max(value = 1, message = "状态只能是0或1")
    @Min(value = 0, message = "状态只能是0或1")
    private Integer status;
    protected Date createTime;
    protected Date updateTime;
    protected Integer optLock;
    @Size(max = 50, message = "描述不能大于50个字符")
    protected String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
