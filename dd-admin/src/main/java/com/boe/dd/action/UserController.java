package com.boe.dd.action;

//import com.boe.dd.bean.Users;
//import com.boe.dd.dao.message.MessageDao;
//import com.boe.dd.service.UserService;

import com.boe.dd.bean.MqConsumerMessageEntity;
import com.boe.dd.bean.MqProducerMessageEntity;
import com.boe.dd.service.distribute.PlatformService;
import com.boe.dd.service.failedMessage.FailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by QrCeric on 16/5/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {
//    @Resource(name = "messageDaoImpl")
//    private MessageDao messageDao;

    @Resource(name = "failMessage")
    private FailMessage failMessage;

    @Resource(name = "platformService")
    private PlatformService platformService;

    @RequestMapping(value = "/countProducer", method = RequestMethod.GET)
    public ModelAndView countProducer() {
        int c = failMessage.getProducerMessage().size();
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Error Messages from Producer: " + c);
        mv.setViewName("users");
        return mv;
    }

    @RequestMapping(value = "/retryProducer", method = RequestMethod.GET)
    @Transactional
    public ModelAndView count() {
        Integer c = null;
        List<MqProducerMessageEntity> list = failMessage.getProducerMessage();
        for (MqProducerMessageEntity entity : list) {
            c = entity.getId();
            failMessage.retryMessage(entity);
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", c);
        mv.setViewName("users");
        return mv;
    }

    @RequestMapping(value = "/countConsumer", method = RequestMethod.GET)
    public ModelAndView countConsumer() {
        int c = failMessage.getConsumerMessage().size();
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Error Messages from Producer: " + c);
        mv.setViewName("users");
        return mv;
    }


    @RequestMapping(value = "/retryConsumer", method = RequestMethod.GET)
    @Transactional
    public ModelAndView retryConsumer() {
        Integer c = null;
        List<MqConsumerMessageEntity> list = failMessage.getConsumerMessage();
        for (MqConsumerMessageEntity entity : list) {
            c = entity.getId();
            failMessage.retryMessage(entity);
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", c);
        mv.setViewName("users");
        return mv;
    }
}
