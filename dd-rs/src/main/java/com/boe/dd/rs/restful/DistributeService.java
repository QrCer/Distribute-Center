package com.boe.dd.rs.restful;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by QrCeric on 16/4/13.
 * MessageQueue的Rest实现
 * 负责将内容分发到各个平台的接口
 */
@Component
@Path("/producer")
public interface DistributeService {
    /**
     * @return
     * @throws Exception
     */
    @POST
    @Path("/sendtoall")
    @Produces(MediaType.APPLICATION_JSON)
    String sendTagALL(@RequestParam(value = "message") String message);

    @POST
    @Path("/sendtosc")
    @Produces(MediaType.APPLICATION_JSON)
    String sendTagSC(@RequestParam(value = "message") String message);

    @POST
    @Path("/sendtooasis")
    @Produces(MediaType.APPLICATION_JSON)
    String sendTagOASIS(@RequestParam(value = "message") String message);

    @POST
    @Path("/sendmessage")
    @Produces(MediaType.APPLICATION_JSON)
    String sendMessage(String message, List<String> tags);
}
