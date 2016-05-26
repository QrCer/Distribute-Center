package com.boe.dd.bean;


/**
 * 接口平台发送短信请求实体
 *
 * @author Johnson
 */

public class ShortMessageInfoBean4Rs {

    /* 下行目的号码,多个手机号以',(半角)'分割,最多500个，不能为空 */
    private String phones;

    /* 短信内容 ,一条短信最多350个汉字，超过后则自动拆分为多条发送 */
    private String content;

    /* 定时发送，为空则立即发送 格式 yyyy-MM-dd HH:ss:mm */
    private String sendTime;

    private String subCode;

    private String msgId;

    /* notice通知通道账户 promo 营销通道账户  必填*/
    private String type;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
