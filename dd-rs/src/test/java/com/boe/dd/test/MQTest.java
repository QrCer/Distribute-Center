package com.boe.dd.test;

/**
 * Created by QrCeric on 16/4/13.
 */
public class MQTest {
//  Data Distribute服务地址,写在filter

    //单元测试,timeout限制完成的毫秒数
    //Queue测试
//    @Test(timeout = 0)
//    public void testMQCustomerQueue() throws Exception{
//        java.lang.String result=null;
//        try{
//            result = new MQCustomerQueueService().receiveMessage();
//            System.out.println(result.toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //断言,如果result不等于第三个参数,则抛出第一个参数的错误提示.
//        Assert.assertEquals("Error",result,"A Queue Message from ActiveMQQueue[exampleQueue]!");
//    }

    //单元测试,timeout限制完成的毫秒数
//    //Topic测试
//    @Test(timeout = 0)
//    public void testMQCustomerTopic() throws Exception{
//        java.lang.String result=null;
//        try{
//            result = new MQCustomerReceiverService().receiveMessage();
//            System.out.println(result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //断言,如果result不等于第三个参数,则抛出第一个参数的错误提示.
//        Assert.assertEquals("Error",result,"A Topic Message from JMS2!");
//    }

    //测试收发
//    @Test(timeout= 1000)
//    public  void testMQFunction() throws Exception{
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new java.lang.String[]{"spring-rs.xml"});
//        java.lang.String result = null;
//        for (int i = 0; i < 3; i++) {
//            SendResult sendResult = defaultMQProducer.send(message);
//            result = sendResult.toString();
//            System.out.println("id:" + result.getMsgId() + "\nresult:" + result.toString());
//        }
//    }
}
