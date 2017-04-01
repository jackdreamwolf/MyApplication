package com.sec.mp.myapplication.utils;

/**
 * Created by Administrator on 2017/3/7.
 */

public class UrlFactory {
    static UrlFactory url = newInstance();

    public static String sign = "354656e4e2fa453d94ef93ad5fd3ac72";

    public static UrlFactory newInstance() {
        if (url == null) {
            url = new UrlFactory();
        }
        return url;
    }

    public static String getHotlist() {
        String hoturl1 = "https://route.showapi.com/213-4?&showapi_sign=";
        String hoturl = hoturl1 + sign;
        return hoturl;
    }

    public static String getLrc() {
        String hoturl1 = "https://route.showapi.com/213-2?&showapi_sign=";
        String hoturl = hoturl1 + sign;
        return hoturl;
    }

    public static String getSearchList() {
        String hoturl1 = "https://route.showapi.com/213-1?showapi_sign=";
        String hoturl = hoturl1 + sign;
        return hoturl;
    }


}
