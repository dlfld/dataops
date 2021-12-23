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
    //元文件信息为空
    NULL_META_MESSAGE("00006", "元文件信息为空"),
    //文件已经分享
    FILE_LOST("00007", "当前文件丢失"),
    //文件分享错误
    FILE_SHARE_ERROR("00008", "文件拷贝出错请联系管理员"),
    //删除失败,文件不存在！
    FILE_NOT_EXIST("00009", "删除失败,文件不存在"),
    //删除文件失败
    FAILED_TO_DELETE_FILE("00010", "删除文件失败"),
    //当前删除的路径不是一个文件
    NOT_A_FILE("00011", "当前删除的路径并不是一个文件"),
    //文件夹不存在
    PATH_NOT_EXIST("00012", "文件夹不存在"),
    //系统异常，元文件写入失败
    META_WRITE_FAIL("00013", "系统异常，元文件写入失败"),
    //项目不存在
    PROJECT_NOT_FOUND("00014","项目不存在")
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