package com.giggle.comfy.common.result;

import lombok.Getter;

/**
 * 封装 web 返回编码
 * @author shilikun
 * @since 2025-05-16
 */
@Getter
public enum ResultCode {

    SUCCESS_CODE(200,"请求成功"),
    FAIL_CODE(500,"请求失败");
    private final int code;
    private final String msg;

    ResultCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
