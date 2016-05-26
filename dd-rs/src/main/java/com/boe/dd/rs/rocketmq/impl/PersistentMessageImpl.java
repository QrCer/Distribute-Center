package com.boe.dd.rs.rocketmq.impl;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.boe.dd.bean.MqConsumerMessageEntity;
import com.boe.dd.bean.MqProducerMessageEntity;
import com.boe.dd.rs.rocketmq.PersistentMessage;
import com.boe.dd.service.failedMessage.FailMessage;
import com.boe.dd.service.failedMessage.ShortMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by QrCeric on 16/5/18.
 */
@Component("persistentMessage")
public class PersistentMessageImpl implements PersistentMessage {

    @Resource(name = "failMessage")
    private FailMessage failMessage;

    @Resource(name = "shortMessage")
    private ShortMessage shortMessage;

    /**
     * @param mqProducerMessageEntity
     */
    @Override
    public void persistentMessageEntity(MqProducerMessageEntity mqProducerMessageEntity) {
        failMessage.addMessage(mqProducerMessageEntity);
        //短信告警
        shortMessage.alertMessage();
    }

    /**
     * @param mqConsumerMessageEntity
     */
    @Override
    public void persistentMessageEntity(MqConsumerMessageEntity mqConsumerMessageEntity) {
        failMessage.addMessage(mqConsumerMessageEntity);
        //短信告警
        shortMessage.alertMessage();
    }

    /**
     * @param message 失败消息的内容
     * @param status  消息失败的原因
     */
    @Override
    public void persistentProducerMessage(Message message, int status) {
        persistentProducerMessage(message, status, null, null);
    }

    /**
     * @param message    失败消息的内容
     * @param status     消息失败的原因
     * @param sendResult 消息发送的结果
     */
    @Override
    public void persistentProducerMessage(Message message, int status, SendResult sendResult) {
        persistentProducerMessage(message, status, sendResult, null);
    }

    /**
     * @param message    失败消息的内容
     * @param status     消息失败的原因
     * @param sendResult 消息发送的结果
     * @param e          异常内容
     */
    @Override
    public void persistentProducerMessage(Message message, int status, SendResult sendResult, Exception e) {
        MqProducerMessageEntity mqProducerMessageEntity = new MqProducerMessageEntity();
        mqProducerMessageEntity.setMsgId(sendResult.getMsgId());
        mqProducerMessageEntity.setTopic(message.getTopic());
        mqProducerMessageEntity.setTag(message.getTags());
        byte[] body = message.getBody();
        try {
            mqProducerMessageEntity.setBody(null != body ? new String(body, "UTF-8") : "");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        mqProducerMessageEntity.setRawMessage(message.toString());
        mqProducerMessageEntity.setStatus(status);
        mqProducerMessageEntity.setExcetpion(null != e ? e.toString() : "");
        persistentMessageEntity(mqProducerMessageEntity);
    }

    /**
     * @param messageExt 失败消息的内容, extends Message
     * @param status     消息失败的原因
     */
    @Override
    public void persistentConsumerMessage(MessageExt messageExt, int status) {
        persistentConsumerMessage(messageExt, status, null);
    }

    /**
     * @param messageExt 失败消息的内容, extends Message
     * @param status     消息失败的原因
     * @param e          异常内容
     */
    @Override
    public void persistentConsumerMessage(MessageExt messageExt, int status, Exception e) {
        MqConsumerMessageEntity mqConsumerMessageEntity = new MqConsumerMessageEntity();
        mqConsumerMessageEntity.setMsgId(messageExt.getMsgId());
        mqConsumerMessageEntity.setTopic(messageExt.getTopic());
        mqConsumerMessageEntity.setTag(messageExt.getTags());
        byte[] body = messageExt.getBody();
        try {
            mqConsumerMessageEntity.setBody(null != body ? new String(body, "UTF-8") : "");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        mqConsumerMessageEntity.setRawMessage(messageExt.toString());
        mqConsumerMessageEntity.setStatus(status);
        mqConsumerMessageEntity.setExcetpion(null != e ? e.toString() : "");
        persistentMessageEntity(mqConsumerMessageEntity);
    }
}
