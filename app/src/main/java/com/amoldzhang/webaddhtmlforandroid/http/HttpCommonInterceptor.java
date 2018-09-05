package com.amoldzhang.webaddhtmlforandroid.http;



import com.amoldzhang.webaddhtmlforandroid.tools.GHLog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * 拦截器
 *
 * 向请求头里添加公共参数
 * Created by amoldZhang on 2017/7/13.
 */
public class HttpCommonInterceptor implements Interceptor {
    private Map<String,String> mHeaderParamsMap = new HashMap<String,String>();

    public HttpCommonInterceptor(Map<String,String> mHeaderParamsMap) {
       this.mHeaderParamsMap = mHeaderParamsMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        GHLog.i("联网接口调用","url = " + chain.request().url());
        //添加公共参数,添加到header中
        Request.Builder builder = chain.request().newBuilder();
        if (mHeaderParamsMap != null && mHeaderParamsMap.size() > 0) {
            Set<String> keys = mHeaderParamsMap.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, mHeaderParamsMap.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());
    }

}