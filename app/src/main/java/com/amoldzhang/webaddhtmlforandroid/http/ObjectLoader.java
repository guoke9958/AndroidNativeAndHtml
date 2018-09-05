package com.amoldzhang.webaddhtmlforandroid.http;

import com.amoldzhang.webaddhtmlforandroid.http.aes.AESUtils;
import com.amoldzhang.webaddhtmlforandroid.http.api.APIConfig;
import com.amoldzhang.webaddhtmlforandroid.http.utils.DateUtil;
import com.amoldzhang.webaddhtmlforandroid.http.utils.MD5Util;
import com.amoldzhang.webaddhtmlforandroid.tools.GHLog;
import com.google.gson.Gson;
import java.util.LinkedHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 将一些重复的操作提出来，放到父类以免Loader 里每个接口都有重复代码
 * Created by amoldZhang on 2017/7/13.
 */

public class ObjectLoader {

    private String TAG = ObjectLoader.class.toString();

    /**
     * 有返回参数的
     * @param observable
     * @param <T>
     * @return
     */
    public  <T> Observable<T> observe(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     *  无反回参数的
     * @param o
     * @param s
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 执行接口加密
     * @param params
     * @return
     */
    public LinkedHashMap<String, String> setParamsAES(LinkedHashMap<String, String> params){
        //执行接口加密
        params = setParameterEncryption(params);
        return params;
    }

    /**
     * 进行网络参数加密
     *  流程：
     *    1.获取当前时间戳  nowTime (long)
     *    2.将当前时间戳加入近网络连接中的请求参数中  map.add("timeStamp",nowTime)
     *    3.将当前网络请求参数转换成Json数据类型  JSON
     *    4.用MD5对 （固定参数标识(数据库给定) + 参数传换成的Json类型数据 + 生成的时间戳）生成不可逆的密文
     *    5.将生成不可逆的秘文添加入请求参数中
     *    6.对新生成的请求参数进行AES加密
     *    7.将AES密文放入字典data中
     * @param map 当前联网拼接参数
     */
    public LinkedHashMap<String, String> setParameterEncryption(LinkedHashMap<String, String> map) {
        LinkedHashMap<String, String> paramsAES = new LinkedHashMap<String, String>();
        try {
            if (map == null){
                map = new LinkedHashMap<String, String>();
            }
            //生成一个时间戳
            String timeStamp = String.valueOf(DateUtil.getNewTimeLong());
            //将时间戳加入到联网请求中
            map.put("timeStamp",timeStamp);
            // 将当前网络请求参数转换成Json数据类型
            String sourceRequest = new Gson().toJson(map);
            GHLog.i("联网请求参数","sourceRequest = "+sourceRequest);
            String dataJson = (APIConfig.ACCESS_KEY + sourceRequest + timeStamp).replace("\\","");
            //生成不可逆的密文
            String sign = MD5Util.MD5Encode(dataJson, "utf-8");
            //将这个不可逆的密文放入请求参数中
            map.put("sign",sign);
            System.out.println(map);
            //对新生成的请求参数AES加密并放入字典的data中
            if (map != null) {
                String info = new Gson().toJson(map);
                GHLog.i("传入参数","传入参数" + info);
                // 使用AES算法将用户的请求参数加密
                String data = AESUtils.EncryptECB(info);
                paramsAES.put("data", data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramsAES;
    }

    /**
     * 传入map 字段 反回
     * @param map
     * @return
     */
    public String setParameterEncryptionString(LinkedHashMap<String, String> map) {
        String paramsAES = "";
        try {
            LinkedHashMap<String, String> mapparams = setParameterEncryption(map);
            paramsAES = mapparams.get("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramsAES;
    }


}
