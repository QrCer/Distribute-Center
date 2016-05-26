package com.boe.dd.service.failedMessage.impl;

import com.boe.dd.bean.ShortMessageInfoBean4Rs;
import com.boe.dd.service.failedMessage.ShortMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by QrCeric on 16/5/24.
 */
@Service("shortMessage")
public class ShortMessageImpl implements ShortMessage {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean smsSent;

    @Value("${intClient}")
    private String intClient;
    @Value("${sms.content}")
    private String content;
    @Value("${sms.phones}")
    private String phones;

    @Resource
    private RestTemplate restTemplate;

    public void sendShortMsg(ShortMessageInfoBean4Rs msg) {
        String url = intClient + "/sms/sendShortMessage";
        logger.info("========发送短信：调用短信接口开始==================url=" + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(msg, headers);
        try {
            HashMap<String, String> shortMessageResponseBean4Rs = restTemplate.postForObject(url, request, HashMap.class);
            if (shortMessageResponseBean4Rs.get("result").equals("T")) {
                logger.info("sendShortMsg: {}", "异常短信通知成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("exception=========发送短信：调用短信接口==================");
            logger.error("exception========发送短信：调用短信接口失败！==================");
        }
        logger.info("发送短信：调用短信接口结束==================");
    }

    public void sendShortMsgs(List<ShortMessageInfoBean4Rs> msg) {
        String url = intClient + "/sms/sendShortMessages";
        logger.info("========发送短信：调用短信接口开始==================url=" + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List> request = new HttpEntity<List>(msg, headers);
        try {
            String str = restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("exception=========发送短信：调用短信接口==================");
            logger.error("exception========发送短信：调用短信接口失败！==================");
        }
        logger.info("发送短信：调用短信接口结束==================");
    }

    @Override
    public void alertMessage() {
        if (!isSmsSent()) {
            ShortMessageInfoBean4Rs shortMessageInfoBean4Rs = new ShortMessageInfoBean4Rs();
            shortMessageInfoBean4Rs.setContent(content);
            shortMessageInfoBean4Rs.setPhones(phones);
            shortMessageInfoBean4Rs.setType("notice");
            sendShortMsg(shortMessageInfoBean4Rs);
            setSmsSent(true);
        }
    }

    @Override
    public void job() {
        if (isSmsSent()) {
            logger.info("job: {}", "短信告警发送限制为半小时一次,现在已经重置");
            setSmsSent(false);
        }
        logger.info("job: {}", "短信发送重置定时任务执行完毕");
    }

    public boolean isSmsSent() {
        return smsSent;
    }

    public void setSmsSent(boolean smsSent) {
        this.smsSent = smsSent;
    }
}
