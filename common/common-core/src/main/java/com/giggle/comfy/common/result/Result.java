package com.giggle.comfy.common.result;


import lombok.Data;

import java.io.Serializable;

/**
 * 封装 web 返回值
 * @author shilikun
 * @since 2025-05-16
 */
@Data
public class Result<T> implements Serializable {
    private int code;
    private T data;
    private String message;

    public static <T> Result<T> success(T data) {
        return result(ResultCode.SUCCESS_CODE, data);
    }

    public static <T> Result<T> success() {
        return result(ResultCode.SUCCESS_CODE, null);
    }

    public static <T> Result<T> failed() {
        return result(ResultCode.FAIL_CODE, null);
    }

    public static <T> Result<T> failed(ResultCode resultCode) {
        return result(resultCode, null);
    }

    public static <T> Result<T> failed(String message) {
        return result(ResultCode.FAIL_CODE.getCode(), null, message);
    }

    public static <T> Result<T> result(int code, T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> result(ResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setData(data);
        return result;
    }

    public static boolean isSuccess(Result<?> result) {
        return result.getCode() == 200;
    }
}
