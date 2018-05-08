package com.ch.cloud.demo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 *
 * @author liwei
 * @date 2017/6/22
 */
public class RestErrorResultV2 implements Serializable {

    private static final long serialVersionUID = -6547378149634482720L;

    @JsonProperty("err_code")
    public String errCode;

    @JsonProperty("err_msg")
    public String errMsg;

    @JsonProperty("err_desc")
    public String errDesc;

    public RestErrorResultV2() {
    }

    public RestErrorResultV2(String errCode, String errMsg, String errDesc) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
