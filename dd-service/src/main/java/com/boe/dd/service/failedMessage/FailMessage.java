package com.boe.dd.service.failedMessage;

import com.boe.dd.bean.MqConsumerMessageEntity;
import com.boe.dd.bean.MqProducerMessageEntity;

import java.util.List;

/**
 * Created by QrCeric on 16/5/16.
 */
public interface FailMessage {

    List<MqProducerMessageEntity> getProducerMessage();

    List<MqConsumerMessageEntity> getConsumerMessage();

    void addMessage(MqProducerMessageEntity mqProducerMessageEntity);

    void addMessage(MqConsumerMessageEntity mqConsumerMessageEntity);

    Boolean retryMessage(MqProducerMessageEntity mqProducerMessageEntity);

    Boolean retryMessage(MqConsumerMessageEntity mqConsumerMessageEntity);

    Boolean retrySend(String message, String tag);

}
