package com.boe.dd.rs.rocketmq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * Created by QrCeric on 16/5/10.
 * 消费消息,并根据消息需要接收的对象调用相应平台的服务
 */
public interface MQConsumer {

    //消费消息
    ConsumeOrderlyStatus consumeMessage(MessageExt messageExt);
}
