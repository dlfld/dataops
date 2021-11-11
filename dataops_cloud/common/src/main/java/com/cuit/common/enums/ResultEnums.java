package com.cuit.common.enums;


/**
 * @author dailinfeng
 */

public enum ResultEnums {
    //成功
    SUCCESS("200", "请求成功"),
    //失败
    ERROR("1111", "请求失败"),
    //系统异常
    SYSTEM_ERROR("1000", "系统异常"),
    //没有登陆
    USER_NOT_FOUND("00001", "无此用户"),
    //没有登陆
    HAVE_NO_TOKEN("00002", "没有登陆"),
    //密码错误
    PASSWORD_ERROR("00003", "密码错误"),
    //用户已经注册
    USER_EXIST("00004", "用户已存在"),
    //用户未登录
    USER_NO_LOGIN("00005", "用户未登录"),
    ;
    private String code;
    private String msg;

    ResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}