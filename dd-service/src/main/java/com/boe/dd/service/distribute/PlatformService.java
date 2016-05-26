package com.boe.dd.service.distribute;

/**
 * Created by QrCeric on 16/5/6.
 */
public interface PlatformService {

    String sendToALL(String string);

    String sendToSC(String string);

    String sendToOASIS(String string);

    String sendToSelf(String string);
}
