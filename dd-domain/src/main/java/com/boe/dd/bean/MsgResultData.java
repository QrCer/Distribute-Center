package com.boe.dd.bean;

/**
 * MsgResult 返回数据对象
 *
 * @author peixf
 */
public class MsgResultData {
    //保存每条错误数据的状态，MsgResult.FAIL代表失败
    private String status;
    //保存每条错误数据的错误信息，不同的记录可能出错原因不同
    private String message;
    //value默认使用主键的值，主键为多个时，使用逗号分割多个主键值，考虑到兼容性用Object
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
