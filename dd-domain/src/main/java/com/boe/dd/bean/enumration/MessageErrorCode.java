package com.boe.dd.bean.enumration;

/**
 * Created by QrCeric on 16/5/18.
 */
public enum MessageErrorCode {
    //  发送成功,1
    Success,
    //  Producer处理失败,21
    Producer_Failed,
    //  Producer处理失败,带异常信息,29
    Producer_Exception,
    //  Consumer处理失败,31
    Consumer_Failed,
    //  Consumer处理失败,带异常信息,39
    Consumer_Exception
}
