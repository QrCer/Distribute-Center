package com.boe.dd.rs.restful.impl;

import com.boe.dd.rs.restful.DistributeService;
import com.boe.dd.rs.rocketmq.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by QrCeric on 16/4/13.
 */
@Component
public class DistributeServiceImpl implements DistributeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MQProducer mqProducer;


    @Override
    public String sendTagALL(String message) {
        logger.info("sentToALL: {}", message);
        mqProducer.sendTagAll(message);
        return HttpStatus.OK + "成功ALL";
    }

    @Override
    public String sendTagSC(String message) {
        logger.info("sentToSC: {}", message);
        mqProducer.sendTagSC(message);
        return "成功SC";
    }

    @Override
    public String sendTagOASIS(String message) {
        logger.info("sentToOASIS: {}", message);
        mqProducer.sendTagOASIS(message);
        return "成功OASIS";
    }

    @Override
    public String sendMessage(String message, List<String> tags) {
        logger.info("sendMessage to {} /n message: {},", message, tags.toString());
        mqProducer.sendTo(message, tags);
        return "发送给" + tags.toString();
    }
}
