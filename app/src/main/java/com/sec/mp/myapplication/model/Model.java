package com.sec.mp.myapplication.model;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sec.mp.myapplication.utils.CCRequestParams;
import com.sec.mp.myapplication.utils.UrlFactory;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by Administrator on 2017/3/7.
 */

public class Model {
    public void LoadgangtaiList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("topid", "6");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadgangtaiList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public void LoadhanguoList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("topid", "16");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadhanguoList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public void LoadjanpenList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("topid", "17");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadjanpenList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public void LoadminyaoList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("topid", "18");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadminyaoList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public void LoadruckList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("topid", "19");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadruckList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }


    public void LoadoumeiList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("topid", "3");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadoumeiList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public void LoadlandList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("topid", "5");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadlandList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public void LoadSloeList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("topid", "23");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadSloeList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    public void LoadHotList(final Handler handler, final int successfulWhat, final int failWhat) {
//        ?showapi_appid=27981&showapi_timestamp=20170308112257&topid=26&showapi_sign=ed70d8d8bec82696f1e2a9983b6ecbe5
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getHotlist());
          params.addBodyParameter("showapi_appid", "27981");
          params.addBodyParameter("topid", "26");
        // params.addBodyParameter("showapi_sign", "354656e4e2fa453d94ef93ad5fd3ac72");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadHotList(handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public void LoadSearchList(final String keyword,final Handler handler, final int successfulWhat, final int failWhat) {
//        https://route.showapi.com/213-1?keyword=海阔天空&page=1&showapi_appid=27981&showapi_sign="
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getSearchList());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("keyword", keyword);
        params.addBodyParameter("page", "1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadSearchList(keyword,handler, successfulWhat, failWhat);
                } else {
                    success(result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public void LoadLyric(final int id, final Handler handler, final int successfulWhat, final int failWhat) {
//        https://route.showapi.com/213-2?musicid=200380820&showapi_appid=27981&showapi_sign=";
        RequestParams params = new CCRequestParams(UrlFactory.newInstance().getLrc());
        params.addBodyParameter("showapi_appid", "27981");
        params.addBodyParameter("musicid", String.valueOf(id));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!signIsTrue(result)) {
                    LoadLyric(id, handler, successfulWhat, failWhat);
                } else {
                    success(id, result, handler, successfulWhat, failWhat);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                error(ex, isOnCallback, handler, failWhat);
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 当sign错误 修改为正确
     *
     * @param result
     * @return
     */
    private boolean signIsTrue(String result) {
        JSONObject jo = JSONObject.parseObject(result);
        if (jo.getInteger("showapi_res_code") != 0) {
            String showapi_res_error = jo.getString("showapi_res_error");
            final String[] b = new String[1];
            String a = showapi_res_error;
            b[0] = a.substring(a.length() - 32);
            UrlFactory.sign = b[0];
            return false;
        }
        return true;
    }

    private void success(String resul, Handler handler, int successWhat, int failWhat) {
        JSONObject jo;
        Message msg = handler.obtainMessage();
        jo = JSONObject.parseObject(resul);
        if (jo.containsKey("showapi_res_code")) {
            try {
                if ("0".equals(jo.getString("showapi_res_code"))) {
                    msg.what = successWhat;
                    msg.obj = resul;
                }
                handler.sendMessage(msg);
            } catch (Exception e) {
                msg.obj = "数据解析错误";
                msg.what = failWhat;
                handler.sendMessage(msg);

            }
        } else {
            msg.obj = "数据解析错误";
            msg.what = failWhat;
            handler.sendMessage(msg);
        }


    }

    private void success(int id, String resul, Handler handler, int successWhat, int failWhat) {
        JSONObject jo;

        Message msg = handler.obtainMessage();
        jo = JSONObject.parseObject(resul);
        if (jo.containsKey("showapi_res_code")) {
            try {
                if ("0".equals(jo.getString("showapi_res_code"))) {
                    msg.what = successWhat;
                    msg.obj = resul;
                }
                handler.sendMessage(msg);
            } catch (Exception e) {
                msg.obj = "数据解析错误";
                msg.what = failWhat;
                handler.sendMessage(msg);

            }
        } else {
            msg.obj = "数据解析错误";
            msg.what = failWhat;
            handler.sendMessage(msg);
        }


    }

    private void error(Throwable ex, boolean isOnCallback, Handler handler, int failWhat) {
        if (ex instanceof HttpException) { // 网络错误
            Message msg = handler.obtainMessage();
            msg.obj = "请求错误";
            msg.what = failWhat;
            handler.sendMessage(msg);
        } else if (ex instanceof SocketTimeoutException) { // 网络错误
            Message msg = handler.obtainMessage();
            msg.obj = "网络超时，请稍后重试。";
            msg.what = failWhat;
            handler.sendMessage(msg);
        } else if (ex instanceof Resources.NotFoundException) {
            Message msg = handler.obtainMessage();
            msg.obj = "无法找到资源";
            msg.what = failWhat;
            handler.sendMessage(msg);
        } else if (ex instanceof JSONException) {
            Message msg = handler.obtainMessage();
            msg.obj = "获取数据格式错误";
            msg.what = failWhat;
            handler.sendMessage(msg);

        } else if (ex instanceof NullPointerException) {
            Message msg = handler.obtainMessage();
            msg.obj = "NullPointerException";
            msg.what = failWhat;
            handler.sendMessage(msg);

        } else if (ex instanceof ConnectException) {
            Message msg = handler.obtainMessage();
            msg.obj = "无法连接到服务器";
            msg.what = failWhat;
            handler.sendMessage(msg);

//            LogUtils.D(isOnCallback);
        } else {
            Message msg = handler.obtainMessage();
            msg.obj = "other error";
            msg.what = failWhat;
            handler.sendMessage(msg);
        }

    }
}
