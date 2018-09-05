package com.amoldzhang.webaddhtmlforandroid.http;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.amoldzhang.webaddhtmlforandroid.http.api.APIConfig;
import com.amoldzhang.webaddhtmlforandroid.http.api.MovieService;
import com.amoldzhang.webaddhtmlforandroid.http.dialog.ContextUtils;
import com.amoldzhang.webaddhtmlforandroid.http.utils.NetworkUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 说明：
 * 创建了一个RetrofitServiceManager类，该类采用单例模式，在私有的构造方法中，生成了Retrofit 实例，
 * 并配置了OkHttpClient和一些公共配置。提供了一个create（）方法，生成接口实例，接收Class范型，
 * 因此项目中所有的接口实例Service都可以用这个来生成，代码如下：
 * mMovieService = RetrofitServiceManager.getInstance().create(MovieService.class);
 * 通过create()方法生成了一个MovieService
 * Created by amoldZhang on 2017/7/13.
 */
public class RetrofitServiceManager {
    private  static final int DEFAULT_TIME_OUT = 60;//超时时间
    private static final int DEFAULT_READ_TIME_OUT = 60;
    private  Retrofit mRetrofit;
    private static String baseUrl = APIConfig.WEB_SERVICE;
    private MovieService apiService;

    public RetrofitServiceManager(Activity mActivity, String webURL) {
        this(mActivity,webURL,null);
    }

    public RetrofitServiceManager(Activity mActivity, Map<String,String> headers) {
        this(mActivity,baseUrl,headers);
    }


    public RetrofitServiceManager(Activity mActivity, String webURL, Map<String, String> mHeaderParamsMap) {
        if (!NetworkUtil.isNetworkAvailable(mActivity)){
            if (TextUtils.isEmpty(webURL)) {
                webURL = baseUrl;
            }
            // 创建 OKHttpClient
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(false)    //错误重连
                    .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)     //连接超时时间  以毫秒计数  MILLISECONDS
                    .writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)  //写操作 超时时间  以秒计数
                    .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)   //读操作 超时时间  以秒计数
                    .connectionPool(new ConnectionPool(5, 10, TimeUnit.SECONDS))  // 这里可以自己设置同时连接的个数和时间，这里5个，和每个保持时间为10s
//                .cookieJar(new NovateCookieManger(context))
//                .cache(cache)
                    .addInterceptor(new HttpCommonInterceptor(mHeaderParamsMap))  //添加公共参数
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(webURL)
                    // 对http请求结果进行统一的预处理
                    .addConverterFactory(ResponseConvertFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            apiService = mRetrofit.create(MovieService.class);
        }else{
            Toast.makeText(mActivity,"当前网络不可用，请检查后再试！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取RetrofitServiceManager
     *  @url  自定义接口
     * @return
     */
    public static RetrofitServiceManager getInstance(Activity mActivity, String webURL){
        return new RetrofitServiceManager(mActivity,webURL);
    }

    /**
     * 获取RetrofitServiceManager
     *  @url  自定义接口
     * @return
     */
    public static RetrofitServiceManager getInstance(Activity mActivity, Map<String,String> headers){
        return new RetrofitServiceManager(mActivity,headers);
    }

    /**
     *  自定义接口和添加请求头
     * @param webURL
     * @param headers
     * @return
     */
    public static RetrofitServiceManager getInstance(Activity mActivity, String webURL, Map<String,String> headers){
        return new RetrofitServiceManager(mActivity,webURL,headers);
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

}