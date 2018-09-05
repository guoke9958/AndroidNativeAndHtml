package com.amoldzhang.webaddhtmlforandroid.http;

import android.app.Activity;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.amoldzhang.webaddhtmlforandroid.common.Config;
import com.amoldzhang.webaddhtmlforandroid.http.aes.AESUtils;
import com.amoldzhang.webaddhtmlforandroid.http.api.APIConfig;
import com.amoldzhang.webaddhtmlforandroid.http.utils.DateUtil;
import com.amoldzhang.webaddhtmlforandroid.http.utils.MD5Util;
import com.amoldzhang.webaddhtmlforandroid.tools.GHLog;
import com.amoldzhang.webaddhtmlforandroid.tools.SharedTools;
import com.amoldzhang.webaddhtmlforandroid.tools.Utils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 接口数据加密模式
 * Created by amoldZhang on 2017/8/10.
 */
public class NetworkEncryptionMode {


    /**
     * 传入map 字段 反回
     * @param map
     * @return
     */
    public static String setParameterEncryptionString(LinkedHashMap<String, String> map) {
        String paramsAES = "";
        try {
            LinkedHashMap<String, String> mapparams = setParameterEncryption(map);
            paramsAES = mapparams.get("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramsAES;
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
    public static LinkedHashMap<String, String> setParameterEncryption(LinkedHashMap<String, String> map) {
        LinkedHashMap<String, String> paramsAES = new LinkedHashMap<String, String>();
        Gson gson = new Gson();
        try {
            //生成一个时间戳
            String timeStamp = String.valueOf(DateUtil.getNewTimeLong());
            //将时间戳加入到联网请求中
            map.put("timeStamp",timeStamp);
            // 将当前网络请求参数转换成Json数据类型
            String sourceRequest = gson.toJson(map);
            GHLog.i("联网请求参数","sourceRequest = "+sourceRequest);
            String dataJson = (APIConfig.ACCESS_KEY + sourceRequest + timeStamp).replace("\\","");
            //生成不可逆的密文
            String sign = MD5Util.MD5Encode(dataJson, "utf-8");
            //将这个不可逆的密文放入请求参数中
            map.put("sign",sign);
            System.out.println(map);
            //对新生成的请求参数AES加密并放入字典的data中
            if (map != null) {
                String info = gson.toJson(map);
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
     * 添加请求头中的公共参数 并返回Map字典
     * @return
     * @throws Exception
     */
    public static Map<String,String> setWebHeard(Activity mContext){
        Map<String,String> heardMap = new HashMap<String, String>();
        try {
            heardMap.put("deviceId", Build.BRAND);  //设备型号
            heardMap.put("machineCode", Utils.GetDeviceID(mContext));  //机器码
            heardMap.put("platform","android");  //设备类型
            heardMap.put("appName","lmb");  //应用名称
            heardMap.put("appVersion",new Utils().getVersionName(mContext));   //当前应用版本号
            heardMap.put("platformVersion", Build.VERSION.RELEASE);         //当前设备版本号
            heardMap.put("channel",new Utils().getAppMetaData(mContext, Config.CHANNEL_KEY)); //获取打包渠道名称
            heardMap.put("apiVersion", APIConfig.API_VERSION);   //协议版本号
            String userId = SharedTools.getStringValue(mContext,"userId","-1");
            if(!userId.equals("-1")){
                heardMap.put("userId",userId);  //用户id
            }
            String cityId = SharedTools.getStringValue(mContext,"cityId","-1");
            if(!userId.equals("-1")){
                heardMap.put("cityId",cityId); //城市id
            }
        } catch (Exception e) {
           GHLog.e("消息头中公共参数添加",e.toString());
        }
        return heardMap;
    }
}
