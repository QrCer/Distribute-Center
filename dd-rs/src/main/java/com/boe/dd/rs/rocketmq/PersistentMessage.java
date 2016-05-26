package com.boe.dd.rs.rocketmq;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.boe.dd.bean.MqConsumerMessageEntity;
import com.boe.dd.bean.MqProducerMessageEntity;

/**
 * Created by QrCeric on 16/5/18.
 * 用于将发送失败的消息保存到数据库
 */
public interface PersistentMessage {

    //保存Producer失败的消息
    void persistentMessageEntity(MqProducerMessageEntity mqProducerMessageEntity);

    //保存Consumer失败的消息
    void persistentMessageEntity(MqConsumerMessageEntity mqConsumerMessageEntity);

    //保存Producer失败的消息
    void persistentProducerMessage(Message message, int status);

    //保存Producer失败的消息
    void persistentProducerMessage(Message message, int status, SendResult sendResult);

    //保存Producer失败的消息
    void persistentProducerMessage(Message message, int status, SendResult sendResult, Exception e);

    //保存Consumer失败的消息
    void persistentConsumerMessage(MessageExt messageExt, int status);

    //保存Consumer失败的消息
    void persistentConsumerMessage(MessageExt messageExt, int status, Exception e);
}
