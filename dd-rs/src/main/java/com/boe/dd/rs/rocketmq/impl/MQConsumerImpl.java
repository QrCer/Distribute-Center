package com.boe.dd.rs.rocketmq.impl;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.boe.dd.bean.enumration.TopicTag;
import com.boe.dd.rs.rocketmq.MQConsumer;
import com.boe.dd.rs.rocketmq.PersistentMessage;
import com.boe.dd.service.distribute.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by QrCeric on 16/5/10.
 */
@Component
public class MQConsumerImpl implements MQConsumer {

    //从DD发送给各大平台RS
    @Resource
    PlatformService platformService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //NAMESRV地址
    @Value("${rocketMQ.nameSRV}")
    private String NAMESRV_ADDR;
    //Topic的名字
    @Value("${rocketMQ.topicNAME}")
    private String TOPIC_NAME;
    private DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("PushConsumer");

    @Resource(name = "persistentMessage")
    private PersistentMessage persistentMessage;

    @PostConstruct
    public void init() {
        //随容器启动自动初始化
        logger.info("Consumer init with: {}", NAMESRV_ADDR);
        //设置NAMESRV
        defaultMQPushConsumer.setNamesrvAddr(NAMESRV_ADDR);
        try {
            //设置订阅发送给所有人或指定平台的消息
            defaultMQPushConsumer.subscribe(TOPIC_NAME, "*");
            //一个订阅者可以订阅多个Topic及Tag
            //defaultMQPushConsumer.subscribe(TOPIC_NAME2,Tags2);
            //设置消费消息的方式
            defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            defaultMQPushConsumer.setConsumeMessageBatchMaxSize(100);
//            defaultMQPushConsumer.setPullInterval(15000);//200000 error
            //注册监听器
            defaultMQPushConsumer.registerMessageListener((List<MessageExt> msgs, ConsumeOrderlyContext context) -> {
                //循环消费消息
                Boolean dealing = false;
                for (MessageExt messageExt : msgs) {
                    logger.info("----------Message got ID: {}, Body: {}, \n\tContent: {}", messageExt.getMsgId(), new String(messageExt.getBody()), messageExt.toString());
                    consumeMessage(messageExt);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            });
            defaultMQPushConsumer.start();
        } catch (Exception e) {
            logger.info("e = {}", e);
        }
    }

    @PreDestroy
    public void destroy() throws MQClientException {
        logger.info("destroy: {}", defaultMQPushConsumer.toString());
        //随容器关闭自动销毁
        if (!defaultMQPushConsumer.getNamesrvAddr().isEmpty()) {
            defaultMQPushConsumer.shutdown();
        }
    }

    //消费消息
    public ConsumeOrderlyStatus consumeMessage(MessageExt messageExt) {
        TopicTag topicTag = TopicTag.valueOf(messageExt.getTags());
        ConsumeOrderlyStatus status = null;
        try {
            String body = new String(messageExt.getBody(), "UTF-8");
            logger.info("consumeMessage---body: {}", body);
            switch (topicTag) {
                case BOE_SC:
                    logger.info("ConsumeMessage: {}", "BOE_SC");
                    platformService.sendToSC(body);
//                    status= ConsumeOrderlyStatus.SUCCESS;
                    break;
                case BOE_OASIS:
                    logger.info("ConsumeMessage: {}", "BOE_OASIS");
                    platformService.sendToOASIS(body);
//                    status= ConsumeOrderlyStatus.SUCCESS;
                    break;
                case BOE_IMK:
                    break;
                case BOE_ALL:
                    logger.info("ConsumeMessage: {}", "BOE_ALL");
//                    return ConsumeOrderlyStatus.SUCCESS;
                default:
                    logger.info("ConsumeMessage: {}", "Default");
                    platformService.sendToALL(body);
                    //默认处理,如果没有写成ALL
                    status = ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
        } catch (Exception e) {
            //TODO:消费失败告警处理,发送给告警接口
            logger.info("consumeMessage!!!Exception: {}", e);
            persistentMessage.persistentConsumerMessage(messageExt, 29, e);
            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        }
        status = ConsumeOrderlyStatus.SUCCESS;
        if (!ConsumeOrderlyStatus.SUCCESS.equals(status)) {
            persistentMessage.persistentConsumerMessage(messageExt, 22);
//        TODO:如果消费失败打印日志,否则影响性能
            logger.info("consumeMessage!!!error: {}", "");
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }

}
