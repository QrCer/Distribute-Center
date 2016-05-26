Data Distribution 数据分发

  `com.boe.dd.rs.Alibaba.RocketMQ`
 RocketMQ的测试代码

  `com.boe.dd.rs.Apache.Artemis` 
Artemis的测试代码

	queue是queue模式
	topic是topic模式

非持久化消息：
ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
持久化消息：
ConsumeOrderlyStatus.SUCCESS;

消息示例:
09:57:26,983 INFO  [rocketmq.impl.MQProducerImpl] Message sent ID: 0A01C74500002A9F000000000033018C, Body: Test message, 测试消息,
	Content: SendResult [sendStatus=SEND_OK, msgId=0A01C74500002A9F000000000033018C, messageQueue=MessageQueue [topic=PushTopic, brokerName=broker-a, queueId=0], queueOffset=3068]
09:57:27,057 INFO  [web.context.ContextLoader] Root WebApplicationContext: initialization completed in 1153 ms
[2016-05-10 09:57:27,100] Artifact dd-rs:war exploded: Artifact is deployed successfully
[2016-05-10 09:57:27,100] Artifact dd-rs:war exploded: Deploy took 2,842 milliseconds
09:57:27,140 INFO  [rocketmq.impl.MQConsumerImpl] Message receive ID: 0A01C74500002A9F000000000033018C, Body: Test message, 测试消息,
	Content: MessageExt [queueId=0, storeSize=156, queueOffset=3068, sysFlag=0, bornTimestamp=1462845446970, bornHost=/10.1.199.69:59127, storeTimestamp=1462845446978, storeHost=/10.1.199.69:10911, msgId=0A01C74500002A9F000000000033018C, commitLogOffset=3342732, bodyCRC=193091834, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message [topic=PushTopic, flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=3069, KEYS=0, WAIT=true, TAGS=BOE_ALL}, body=26]]