package com.boe.dd.rs.rocketmq;

import com.alibaba.rocketmq.client.producer.SendResult;

import java.util.List;

/**
 * Created by QrCeric on 16/5/9.
 */
public interface MQProducer {

    SendResult sendTagAll(String body);

    SendResult sendTagSC(String body);

    SendResult sendTagOASIS(String body);

    boolean sendTo(String body, List<String> tags);

//    boolean sendTo(String body,String tag);
}
