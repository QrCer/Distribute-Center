package com.boe.dd.service.failedMessage.impl;

import com.boe.dd.bean.MqConsumerMessageEntity;
import com.boe.dd.bean.MqProducerMessageEntity;
import com.boe.dd.dao.message.impl.MessageDaoImpl;
import com.boe.dd.service.distribute.PlatformService;
import com.boe.dd.service.failedMessage.FailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by QrCeric on 16/5/16.
 */
@Service("failMessage")
public class FailMessageImpl extends MessageDaoImpl implements FailMessage {

    @Resource
    PlatformService platformService;

    @Override
    public List<MqProducerMessageEntity> getProducerMessage() {
        return getProducerDao();
    }

    @Override
    public List<MqConsumerMessageEntity> getConsumerMessage() {
        return getConsumerDao();
    }

    @Override
    public void addMessage(MqProducerMessageEntity mqProducerMessageEntity) {
        save(mqProducerMessageEntity);
    }

    @Override
    public void addMessage(MqConsumerMessageEntity mqConsumerMessageEntity) {
        save(mqConsumerMessageEntity);
    }

    @Override
    public Boolean retryMessage(MqProducerMessageEntity mqProducerMessageEntity) {
        String message = mqProducerMessageEntity.getBody();
        String tag = mqProducerMessageEntity.getTag();
        retrySend(message, tag);
        return null;
    }

    @Override
    public Boolean retryMessage(MqConsumerMessageEntity mqConsumerMessageEntity) {
        String message = mqConsumerMessageEntity.getBody();
        String tag = mqConsumerMessageEntity.getTag();
        retrySend(message, tag);
        return null;
    }

    @Override
    public Boolean retrySend(String message, String tag) {
        System.out.print("retryMessage:" + message + tag);
        switch (tag) {
            case "BOE_SC":
                platformService.sendToSC(message);
//                    status= ConsumeOrderlyStatus.SUCCESS;
                break;
            case "BOE_OASIS":
                platformService.sendToOASIS(message);
//                    status= ConsumeOrderlyStatus.SUCCESS;
                break;
            case "BOE_IMK":
                break;
            case "BOE_ALL":
//                    return ConsumeOrderlyStatus.SUCCESS;
            default:
                platformService.sendToALL(message);
                //默认处理,如果没有写成ALL
        }
        return true;
    }

}
