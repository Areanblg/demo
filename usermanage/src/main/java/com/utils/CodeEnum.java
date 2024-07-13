package com.utils;

public enum CodeEnum {

    SUCCESS(200, "成功"),
    ERROR(500, "服务器错误"),
    NOT_FOUND(404, "未找到"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    BAD_REQUEST(400, "请求错误"),
    USER_ERROR(501, "用户名或密码错误");

    private final int code;
    private final String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
