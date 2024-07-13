package com.utils;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Result <T> {
    private int code;
    private String message;
    private T data;

    public Result() {}

    public Result(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public Result(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 静态工厂方法
    public static <T> Result<T> success() {
        return new Result<>(CodeEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(CodeEnum.SUCCESS, data);
    }

    public static <T> Result<T> error() {
        return new Result<>(CodeEnum.ERROR);
    }
    public static <T> Result<T> usernameError() {
        return new Result<>(CodeEnum.USER_ERROR);
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(CodeEnum.ERROR, data);
    }
}
