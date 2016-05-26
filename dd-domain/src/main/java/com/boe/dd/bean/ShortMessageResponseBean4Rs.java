package com.boe.dd.bean;

import java.util.List;

/**
 * 接口平台发送短信返回消息实体
 *
 * @author Johnson
 */
public class ShortMessageResponseBean4Rs {

    /* 提交短信失败 */
    public static final String FAIL = "F";

    /* 提交短信成功 */
    public static final String SUCCESS = "T";

    /* 短信编号 多个id以 ',(半角)'分割 */
    private String msgid;

    /*
     * 短信提交结果 T——提交成功 F-提交失败
     */
    private String result;

    /*
     * 附加描述信息
     *
     * 返回信息 状态识别码：描述信息 0:信息提交成功 1:接口平台异常,请联系系统管理员 4:存在无效手机号码, 请检查后重新发送
     * 5:手机号码个数超过最大限制, 请检查后重新发送 6:短信内容超过最大限制, 请检查后重新发送 10:内容包含敏感词, 请检查后重新发送
     */
    private String desc;

    /* 接收号码 */
    private String phone;

    private List<DetailInfo> details;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<DetailInfo> getDetails() {
        return details;
    }

    public void setDetails(List<DetailInfo> details) {
        this.details = details;
    }

    public static class DetailInfo {

        private String phoneNumber;
        private String status;
        private String statusDesc;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }

    }

}
