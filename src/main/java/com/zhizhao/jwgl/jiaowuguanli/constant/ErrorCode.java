package com.zhizhao.jwgl.jiaowuguanli.constant;

public enum ErrorCode {
    NoAuthorization(401, "认证失败"),
    NoAuthentication(403, "没有权限"),
    NoPermission(100,"权限错误"),
    ServerError(101,"服务器内部错误"),
    RequestParamsInvalid(102,"参数缺失或不合法"),
    PasswordInvalid(1021,"密码错误"),
    CommandError(103,"执行命令错误"),
    IllegalAction(106,"非法操作"),
    DataAlreadyExisted(1000,"数据已经存在"),
    DataNotFound(1001,"数据不存在"),
    WXAccountAlreadyExisted(10003,"该微信账户已经存在"),
    PhoneHasBeenUsed(11004,"该手机号已经被使用");

    private int code;

    private String description;

    ErrorCode(int code, String description) { this.code = code; this.description = description; }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
