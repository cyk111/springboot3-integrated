package com.cyk.springboot3.mutil.datasources.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 请求上下文
 */
public class RequestContextHolder {
    private static ThreadLocal<String> currentURILocal = new ThreadLocal<>();
    private static ThreadLocal<String> currentHostLocal = new ThreadLocal<>();
    private static ThreadLocal<String> currentUserLocal = new ThreadLocal<>();

    /**
     * 清理相关Local数据
     */
    public static void clear() {
        currentURILocal.remove();
        currentHostLocal.remove();
        currentUserLocal.remove();
    }

    public static void currentURI(String currentURI) {
        currentURILocal.set(currentURI);
    }

    public static String currentURI() {
        return currentURILocal.get();
    }

    public static void currentHost(String currentHost) {
        currentHostLocal.set(currentHost);
    }

    public static String currentHost() {
        return currentHostLocal.get();
    }

    public static void currentUser(String currentUser) {
        currentUserLocal.set(currentUser);
    }

    public static String currentUser() {
        return currentUserLocal.get();
    }



    public static JSONObject context() {
        JSONObject context = new JSONObject();
        context.put("URI", currentURI());
        context.put("Host", currentHost());
        context.put("User", currentUser());
        return context;
    }

}
