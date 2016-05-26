package com.boe.dd.dao.message.impl;

import com.boe.dd.bean.MqConsumerMessageEntity;
import com.boe.dd.bean.MqProducerMessageEntity;
import com.boe.dd.dao.message.MessageDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

//import com.boe.dd.bean.Users;

/**
 * Created by QrCeric on 16/4/26.
 */
@Repository("messageDaoImpl")
@Transactional
public abstract class MessageDaoImpl implements MessageDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

//    @Override
//    public List<MqProducerMessageEntity> getMessage() {
//        Session session = sessionFactory.getCurrentSession();
//        Criteria criteria = session.createCriteria(MqConsumerMessageEntity.class);
//        return criteria.list();
//    }

    @Override
    public void save(Object o) {
        this.getSessionFactory().getCurrentSession().save(o);
    }

    @Override
    public List<MqProducerMessageEntity> getProducerDao() {
        return this.getSessionFactory().openSession().createCriteria(MqProducerMessageEntity.class).list();
    }

    @Override
    public List<MqConsumerMessageEntity> getConsumerDao() {
        return this.getSessionFactory().openSession().createCriteria(MqConsumerMessageEntity.class).list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
