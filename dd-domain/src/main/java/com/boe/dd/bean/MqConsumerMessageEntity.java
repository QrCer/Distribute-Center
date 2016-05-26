package com.boe.dd.bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by QrCeric on 16/5/18.
 */
@Entity
@SequenceGenerator(name = "SEQUENCECON", sequenceName = "seqMQPRODUCER", allocationSize = 1)
@Table(name = "MQ_CONSUMER_MESSAGE")
public class MqConsumerMessageEntity {
    private Integer id;
    private String msgId;
    private String topic;
    private String tag;
    private String body;
    private Integer status;
    private String excetpion;
    private Integer isDeleted;
    private Timestamp createTime;
    private Timestamp deleteTime;
    private String rawMessage;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCECON")
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MSG_ID", nullable = true, length = 255)
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Basic
    @Column(name = "TOPIC", nullable = true, length = 255)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "TAG", nullable = true, length = 255)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "BODY", nullable = true, columnDefinition = "clob")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "STATUS", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "EXCEPTION", nullable = true, length = 255)
    public String getExcetpion() {
        return excetpion;
    }

    public void setExcetpion(String excetpion) {
        this.excetpion = excetpion;
    }

    @Basic
    @Column(name = "IS_DELETED", nullable = true)
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Basic
    @Column(name = "CREATE_TIME", nullable = true, columnDefinition = "timestamp")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "DELETE_TIME", nullable = true, columnDefinition = "timestamp")
    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "RAW_MESSAGE", nullable = true, columnDefinition = "clob")
    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MqConsumerMessageEntity that = (MqConsumerMessageEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (msgId != null ? !msgId.equals(that.msgId) : that.msgId != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (excetpion != null ? !excetpion.equals(that.excetpion) : that.excetpion != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (deleteTime != null ? !deleteTime.equals(that.deleteTime) : that.deleteTime != null) return false;
        return rawMessage != null ? rawMessage.equals(that.rawMessage) : that.rawMessage == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (msgId != null ? msgId.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (excetpion != null ? excetpion.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (deleteTime != null ? deleteTime.hashCode() : 0);
        result = 31 * result + (rawMessage != null ? rawMessage.hashCode() : 0);
        return result;
    }
}
