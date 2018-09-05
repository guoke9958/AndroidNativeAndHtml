package com.amoldzhang.webaddhtmlforandroid.http;


import com.amoldzhang.webaddhtmlforandroid.common.Config;
import com.amoldzhang.webaddhtmlforandroid.http.aes.AESUtils;
import com.amoldzhang.webaddhtmlforandroid.http.api.HttpResult;
import com.amoldzhang.webaddhtmlforandroid.tools.GHLog;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * 联网成功数据解析膜
 * Created by amoldZhang on 2017/7/13.
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            HttpResult httpResult;
            //当BaseUrl.AES等与0 时，执行加密
            if (Config.AES_APP) {
                String responseAES = AESUtils.DecryptECB(response, new Config().password);
                response = (responseAES == null ? response : responseAES.replace("\"entity\":\"\"","\"entity\": {}").replace("\"body\":\"\"","\"body\": {}"));
                GHLog.i("联网反回", response);
            }
            if (response.contains("head") && response.contains("body")){
                if (response != null){
                    JsonReader jsonReader = new JsonReader(new StringReader(response));//其中jsonContext为String类型的Json数据
                    jsonReader.setLenient(true);
                    httpResult = gson.fromJson(jsonReader, HttpResult.class);
                    return (T)httpResult;
                }
            }else{
                httpResult = new HttpResult();
                HttpResult.HeadBean headBean = new HttpResult.HeadBean();
                headBean.setMsg("success");
                headBean.setCmd("");
                headBean.setParam("");
                httpResult.setHead(headBean);
                httpResult.setBody(response);
                return (T)httpResult;
            }
        } catch (Exception e) {
            GHLog.e("数据处理异常", e.toString());
        }finally {
            value.close();
        }
        return null;
    }

}
