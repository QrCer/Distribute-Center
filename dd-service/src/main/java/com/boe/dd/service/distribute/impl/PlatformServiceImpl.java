package com.boe.dd.service.distribute.impl;

import com.boe.dd.service.distribute.PlatformService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by QrCeric on 16/5/6.
 */
@Service("platformService")
public class PlatformServiceImpl implements PlatformService {

    Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Resource
    private RestTemplate restTemplate;

    @Override
    public String sendToALL(String string) {
        logger.info("sendToALL: {}", string);
        restTemplate.postForObject("http://localhost:8080/producer/sendtosc", string, String.class);
        return null;
    }

    @Override
    public String sendToSC(String string) {
        logger.info("sendToSC: {}", string);
//        for (int i=0;i<1000;i++){
//        restTemplate.postForObject("http://localhost:8080/producer/sendtooasis", string, String.class);
//        }
        return null;
    }

    @Override
    public String sendToOASIS(String string) {
        logger.info("sendToOASIS: {}", string);
//        restTemplate.postForObject("http://localhost:8080/producer/sendtosc", null, String.class, string);
        return null;
    }

    //    测试用的服务
    @Override
    public String sendToSelf(String string) {
        logger.info("String = {}", string);
        restTemplate.getForObject("http://localhost:8080/producer/sendtosc", String.class, string);
        restTemplate.postForObject("http://localhost:8080/producer/sendtosc", string, String.class);
        return null;
    }
}
