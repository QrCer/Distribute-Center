package com.boe.dd.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class MsgResult {
    //protected Type type;
    public static String SUCCESS = "1";
    public static String FAIL = "0";

    private String code;
    private String message;

    private List<MsgResultData> data;

    public MsgResult() {

    }

    public MsgResult(String code) {
        this.code = code;
    }

    public MsgResult(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public static MsgResult success() {
        return new MsgResult(MsgResult.SUCCESS);
    }

    public static MsgResult success(String message) {
        return new MsgResult(MsgResult.SUCCESS, message);
    }

    public static MsgResult error(String message) {
        return new MsgResult(MsgResult.FAIL, message);
    }

	/*public MsgResult(Type type) {
        this.type = type;
	}

	public MsgResult(Type type, String msg) {
		this.type = type;
		this.message = msg;
	}*/

	/*public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MsgResultData> getData() {
        return data;
    }

    public void setData(List<MsgResultData> data) {
        this.data = data;
    }

    // public Msg(String code, String msg) {
    // this.code = code;
    // this.message = msg;
    // }

}
