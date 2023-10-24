package com.cyk.springboot3.integrated.i18N.common;


public enum ResponseState {

    /**
     * 系统通用响应代码段 1xxxx
     */
    RESPONSE_SUCCESS(0,                                             "success", "成功"),
    RESPONSE_OK(200,                                             "success", "成功"),
    RESPONSE_NO_LOGIN(401,                                             "NotLogin", "请登录"),
    RESPONSE_FORBIDDEN(403,                                             "FORBIDDEN", "无权限"),
    RESPONSE_SYSTEM_ERROR(500,                                    "System error", "系统错误");



    public final int code;

    public final String englishMessage;

    public final String chineseMessage;

    ResponseState(int code, String englishMessage, String chineseMessage){
        this.code = code;
        this.englishMessage = englishMessage;
        this.chineseMessage = chineseMessage;
    }

    public int getCode() {
        return code;
    }
}
