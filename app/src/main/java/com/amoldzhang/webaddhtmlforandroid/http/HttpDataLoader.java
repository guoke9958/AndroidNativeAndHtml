package com.amoldzhang.webaddhtmlforandroid.http;

import android.app.Activity;

import com.amoldzhang.webaddhtmlforandroid.http.api.MovieService;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *  网络连接设置
 * Created by amoldZhang on 2017/7/13.
 */
public class HttpDataLoader extends ObjectLoader {
    private Type type;
    private MovieService mNetWorkService;

    /**
     * 自定义默认接口
     * @param webURL
     */
    public HttpDataLoader(Activity mActivity, String webURL, Type type){
        this.type = type;
        mNetWorkService = RetrofitServiceManager.getInstance(mActivity,webURL).create(MovieService.class);
    }

    /**
     *  使用默认接口 添加消息头
     * @param headers  添加请求消息头
     */
    public HttpDataLoader(Activity mActivity, Map<String,String> headers, Type type){
        this.type = type;
        mNetWorkService = RetrofitServiceManager.getInstance(mActivity,headers).create(MovieService.class);
    }

    /**
     * 自定义接口连接  添加消息头
     * @param webURL   要修改的接口连接
     * @param headers  传入的请求消息头
     */
    public HttpDataLoader(Activity mActivity, String webURL, Map<String,String> headers, Type type){
        this.type = type;
        mNetWorkService = RetrofitServiceManager.getInstance(mActivity,webURL,headers).create(MovieService.class);
    }

    /**
     * 自定义接口 GET 网络请求
     * @param url     接口名称
     * @param params  要传入的参数
     * @param flage   参数是否加密
     * @return
     */
    public Observable getHttpData(String url, LinkedHashMap<String,String> params, boolean flage){
        //对接口参数加密
        if (flage){
            params = setParamsAES(params);
        }
        return observe(mNetWorkService.executeGet(url,params)
                .compose(schedulersTransformer())
                .map(new HttpResultFunc(type)));
    }

    /**
     * 自定义接口 POST 网络请求
     * @param url     当前接口
     * @param params  当前传入的参数
     * @param flage   当前参数是否加密
     * @return
     */
    public Observable postHttpData(String url, LinkedHashMap<String,String> params, boolean flage){
        //对接口参数加密
        if (flage){
            params = setParamsAES(params);
        }
        return observe(mNetWorkService.executePost(url,params)
                .compose(schedulersTransformer())
                .map(new HttpResultFunc(type)));
    }

    /**
     * 单文件/图片上传
     * @return
     */
    public  Observable upLoadFile(String URL, String myParamsString , LinkedHashMap<String,String> params, boolean flage) throws Exception {
        // 创建 RequestBody，用于封装构建RequestBody
        File file = new File(myParamsString);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/*"),file);
        //对接口参数加密
        if (flage){
            params = setParamsAES(params);
        }
        return observe(mNetWorkService.upLoadFile(URL,requestFile,params)
                .compose(schedulersTransformer())
                .map(new HttpResultFunc(type)));
    }

//    /**
//     * 多 文件/图片 上传
//     * @return
//     */
//    public Observable upLoadFiles(String URL, List<ImageItem> tempSelectBitmap, LinkedHashMap<String,String> params, boolean flage){
//        //对接口参数加密
//        if (flage){
//            params = setParamsAES(params);
//        }
//
//        List<File> fileList = new ArrayList<File>();
//        if (tempSelectBitmap.size()> 0 ){
//            for (ImageItem imageItem:tempSelectBitmap){
//                if (!imageItem.path.equals("-1")){
//                    File file = Bimp.scal(imageItem.path); //将图片压缩至需要大小
//                    fileList.add(file);
//                }
//            }
//        }
//        MultipartBody multipartBody = filesToMultipartBody(fileList);
//        return observe(mNetWorkService.getFellAddParams(URL,multipartBody,params)
//                .compose(schedulersTransformer())
//                .map(new HttpResultFunc(type)));
//    }

    /**
     * 文件下载
     * @return
     */
    public Observable downloadFile(String URL){
        return observe(mNetWorkService.downloadFile(URL)
                .compose(schedulersTransformer())
                .map(new HttpResultFunc(type)));
    }

    Observable.Transformer schedulersTransformer() {
        return new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable)  observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public  MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (files.size() == 0){
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), "");
            builder.addFormDataPart("files", "files", requestBody);
        }else{
            for (File file : files) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
                builder.addFormDataPart("files", file.getName(), requestBody);
            }
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

}

