package com.boe.dd.rs.rocketmq.impl;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.boe.dd.bean.enumration.TopicTag;
import com.boe.dd.rs.rocketmq.MQProducer;
import com.boe.dd.rs.rocketmq.PersistentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

import static com.boe.dd.bean.enumration.TopicTag.BOE_SC;

/**
 * Created by QrCeric on 16/5/9.
 */
@Component
public class MQProducerImpl implements MQProducer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //NAMESRV地址
    @Value("${rocketMQ.nameSRV}")
    private String NAMESRV_ADDR;
    //Topic的名字
    @Value("${rocketMQ.topicNAME}")
    private String TOPIC_NAME;

    @Resource(name = "persistentMessage")
    private PersistentMessage persistentMessage;

    //实例化Producer,DefaultMQProducer是默认生产者,TransactionMQProducer是事务生产者.
//    private DefaultMQProducer defaultMQProducer = new DefaultMQProducer("Producer");
    private TransactionMQProducer defaultMQProducer = new TransactionMQProducer("Producer");

    //初始化方法
    @PostConstruct
    void init() throws MQClientException {
        //随容器启动自动初始化
        logger.info("Producer init with: {}", NAMESRV_ADDR);
        //设置NAMESRV
        defaultMQProducer.setNamesrvAddr(NAMESRV_ADDR);
//        defaultMQProducer.setRetryTimesWhenSendFailed(1);
        defaultMQProducer.setRetryAnotherBrokerWhenNotStoreOK(true);
        defaultMQProducer.start();
        //todo: body是测试消息
        String body = new String("Test message, 测试消息");
//        sendToAll(body);
    }

    @PreDestroy
    void destroy() throws MQClientException {
        logger.info("destroy: {}", defaultMQProducer.toString());
        //随容器关闭自动销毁
        if (!defaultMQProducer.getNamesrvAddr().isEmpty()) {
            defaultMQProducer.shutdown();
        }
    }

    //发送消息的方法
    public SendResult sendMessage(Message message) {
        //sendResult代表返回值
        SendResult sendResult = new SendResult();
        logger.info("==========sendMessage: {}", message.getBody());
        try {
            sendResult = defaultMQProducer.send(message);
        } catch (Exception e) {
            logger.info("sendMessage Exception: {}", e);
            //消息失败处理
            persistentMessage.persistentProducerMessage(message, 19, sendResult, e);

            return sendResult;
        }
        logger.info("Message sent ID: {}, Body: {}, \n\tContent: {}", sendResult.toString());
        if (!SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            persistentMessage.persistentProducerMessage(message, 12, sendResult);
        }
        return sendResult;
    }

    @Override
    //发送给所有平台的消息
    public SendResult sendTagAll(String body) {
        Message message = new Message(TOPIC_NAME, TopicTag.BOE_ALL.toString(), body.getBytes());
        return sendMessage(message);
    }

    @Override
    //发送给SC平台的消息
    public SendResult sendTagSC(String body) {
        Message message = new Message(TOPIC_NAME, BOE_SC.toString(), body.getBytes());
        return sendMessage(message);
    }

    @Override
    //发送给OASIS平台的消息
    public SendResult sendTagOASIS(String body) {
        Message message = new Message(TOPIC_NAME, TopicTag.BOE_OASIS.toString(), body.getBytes());
        return sendMessage(message);
    }

    @Override
    //todo 定义一个对应字典,0:ALL,1:SC,2:OASIS,
    public boolean sendTo(String body, List<String> tags) {
        tags.forEach((String tag) -> {
                    switch (tag) {
                        case "BOE_SC":
                            sendTagSC(body);
                            break;
                        case "BOE_OASIS":
                            sendTagOASIS(body);
                            break;
                        case "BOE_ALL":
                        default:
                            sendTagAll(body);
                    }
                }
        );
        return true;
    }

//    @Override
//    public boolean sendTo(String body,String tag){
//        Message message = new Message(TOPIC_NAME, TopicTag.BOE_OASIS.toString(), body.getBytes());
//        sendMessage(message);
//        return true;
//    }
}
