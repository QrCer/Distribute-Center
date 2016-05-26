package useless.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import useless.rocketmq.enums.TopicTags;

import java.util.List;

/**
 * Created by QrCeric on 16/4/20.
 */
//@Component
public class ConsumerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //NAMESRV地址
    @Value("${rocketMQ.nameSRV}")
    private String NAMESRV_ADDR;
    //Topic的名字
    @Value("${rocketMQ.topicNAME}")
    private String TOPIC_NAME;

    private DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("PushConsumer");

    //    @PostConstruct
    public void init() {
        //随容器启动自动初始化
        logger.info("init: {}", NAMESRV_ADDR);
        //设置NAMESRV
        defaultMQPushConsumer.setNamesrvAddr(NAMESRV_ADDR);
        try {
            //设置订阅发送给所有人或指定平台的消息
            defaultMQPushConsumer.subscribe(TOPIC_NAME, TopicTags.BOE_ALL + "||" + TopicTags.BOE_SC);
            //一个订阅者可以订阅多个Topic及Tag
            //defaultMQPushConsumer.subscribe(TOPIC_NAME2,Tags2);
            //设置消费消息的方式
            defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //注册监听器
            defaultMQPushConsumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
                //循环消费消息
                for (MessageExt messageExt : msgs) {
                    logger.info("Message receive ID: {}, Body: {}, Content: {}", messageExt.getMsgId(), new String(messageExt.getBody()), messageExt.toString());
                    //TODO: Deal with messages.
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            defaultMQPushConsumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @PreDestroy
    public void destroy() throws MQClientException {
        //随容器关闭自动销毁
        if (!defaultMQPushConsumer.getNamesrvAddr().isEmpty()) {
            logger.info("destroy: {}", defaultMQPushConsumer.toString());
            defaultMQPushConsumer.shutdown();
        }
    }
}
