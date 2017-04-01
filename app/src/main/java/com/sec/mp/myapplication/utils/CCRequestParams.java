package com.sec.mp.myapplication.utils;

import com.blankj.utilcode.utils.StringUtils;
import com.sec.mp.myapplication.base.BaseApplication;
import com.sec.mp.myapplication.contants.SPContants;


import org.xutils.http.RequestParams;
import org.xutils.http.app.ParamsBuilder;

/**
 * Created by Administrator on 2017/3/7.
 */

public class CCRequestParams extends RequestParams {

    public CCRequestParams() {
    }

    public CCRequestParams(String uri) {
        super(uri);
        String cookie = BaseApplication
                .spUtils
                .getString(SPContants.COOKIE, "");
        if (!StringUtils.isEmpty(cookie)) {
            addHeader("Cookie", "ASP.NET_SessionId=" + cookie);
            setUseCookie(false);
        }
    }

    public CCRequestParams(String uri, ParamsBuilder builder, String[] signs, String[] cacheKeys) {
        super(uri, builder, signs, cacheKeys);
    }
}
