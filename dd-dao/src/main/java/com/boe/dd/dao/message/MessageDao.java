package com.boe.dd.dao.message;

import com.boe.dd.bean.MqConsumerMessageEntity;
import com.boe.dd.bean.MqProducerMessageEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QrCeric on 16/4/26.
 */

public interface MessageDao<T extends Serializable> {

//    List<MqProducerMessageEntity> getMessage();

    <T> void save(T t);

    List<MqProducerMessageEntity> getProducerDao();

    List<MqConsumerMessageEntity> getConsumerDao();
}
