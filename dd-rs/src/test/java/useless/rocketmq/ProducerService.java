package useless.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import useless.rocketmq.enums.TopicTags;

/**
 * Created by QrCeric on 16/4/20.
 */

//10.80.20.177:9876
//@Component
public class ProducerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //NAMESRV地址
    @Value("${rocketMQ.nameSRV}")
    private String NAMESRV_ADDR;
    //Topic的名字
    @Value("${rocketMQ.topicNAME}")
    private String TOPIC_NAME;

    //实例化Producer,DefaultMQProducer是默认生产者,TransactionMQProducer是事务生产者.
    private DefaultMQProducer defaultMQProducer = new DefaultMQProducer("Producer");
    private TransactionMQProducer producer = new TransactionMQProducer("Produce");

    //    @PostConstruct
    public void init() throws MQClientException {
        //随容器启动自动初始化
        logger.info("init: {}", NAMESRV_ADDR);
        //设置NAMESRV
        defaultMQProducer.setNamesrvAddr(NAMESRV_ADDR);
        defaultMQProducer.start();
        //body是测试消息
        String body = new String("Test message, 测试消息");
//        MessageToAll(body);//todo 删除测试消息
    }

    //发送消息的方法
    public SendResult Send(Message message) {
        SendResult sendResult = null;
        try {
            //发送消息,sendResult代表返回值
            sendResult = defaultMQProducer.send(message);
            logger.info("Message sent ID: {}, Body: {}, Content: {}", sendResult.getMsgId(), new String(message.getBody()), sendResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //todo 消息处理
        }
        return sendResult;
    }

    //发送给所有平台的消息
    public SendResult MessageToAll(String body) {
        Message message = new Message(TOPIC_NAME, TopicTags.BOE_ALL.toString(), "0", body.getBytes());
        return Send(message);
    }

    //发送给SC平台的消息
    public SendResult MessageToSC(String body) {
        Message message = new Message(TOPIC_NAME, TopicTags.BOE_SC.toString(), "0", body.getBytes());
        return Send(message);
    }

    //发送给OASIS平台的消息
    public SendResult MessageToOASIS(String body) {
        Message message = new Message(TOPIC_NAME, TopicTags.BOE_OASIS.toString(), "0", body.getBytes());
        return Send(message);
    }

    //    @PreDestroy
    public void destroy() throws MQClientException {
        //随容器关闭自动销毁
        if (!defaultMQProducer.getNamesrvAddr().isEmpty()) {
            logger.info("destroy: {}", defaultMQProducer.toString());
            defaultMQProducer.shutdown();
        }
    }

}
