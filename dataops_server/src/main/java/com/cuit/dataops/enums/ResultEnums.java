package com.cuit.dataops.enums;


public enum ResultEnums {
    SUCCESS("200", "请求成功"),
    ERROR("1111", "请求失败"),
    SYSTEM_ERROR("1000", "系统异常"),
    USER_NOT_FOUND("00001", "无此用户"),
    HAVE_NO_TOKEN("00002","没有登陆"),
    SEND_CHECKCODE_SUCCESS("200","验证码发送成功,请在30分钟之内进行验证注册"),
    CHECKCODE_NOT_EXPIRE("00004","请上一条验证码过期之后再请求验证码"),
    CHECKCODE_ERROR("00005","验证码错误"),
    LOGIN_FAIl("00006","用户名或密码错误"),
    USER_HAS_REGISTERED("00007","用户已存在"),


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