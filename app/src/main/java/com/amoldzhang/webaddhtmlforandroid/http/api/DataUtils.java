package com.amoldzhang.webaddhtmlforandroid.http.api;

import android.app.Activity;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  接口请求设置类
 * Created by amoldZhang on 2017/7/24.
 */
public class DataUtils {

    private String webUrl;
    private LinkedHashMap<String, String> myParams;
    private Activity myActivity;

    public abstract static class DataBack<T> {
        public void getData(T t){}
        public void getError(Throwable e){}
    }

    public DataUtils(Activity myActivity) {
        this(myActivity,new LinkedHashMap<String, String>());
    }

    public DataUtils(Activity myActivity, LinkedHashMap<String,String> myParams) {
        this(myActivity, APIConfig.WEB_SERVICE,myParams);
    }

    public DataUtils(Activity myActivity, String webUrl, LinkedHashMap<String,String> myParams) {
        this.myActivity = myActivity;
        this.webUrl = webUrl;
        this.myParams = myParams;
    }


//    /**
//     * APP 红点数获取
//     *
//     * @param dataBack
//     */
//    public void getPushMessageUnreadCount(final DataBack dataBack) {
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.PUSH_MESSAGE_UNREAD_COUNT,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData>() {
//                    @Override
//                    public void onNext(APPData data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//
//    }
//
//    /**
//     * APP 红点阅读状态
//     *
//     * @param dataBack
//     */
//    public void getPushMessageReadMessage(final DataBack dataBack) {
//        Type type = new TypeToken<APPData<UserMessage>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.PUSH_MESSAGE_READ_MESSAGE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserMessage>>() {
//                    @Override
//                    public void onNext(APPData<UserMessage> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("operate060005")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * app电话上报接口
//     *
//     * @param dataBack
//     */
//    public void getUserCallRecordCreate(final DataBack dataBack) {
//        Type type = new TypeToken<APPData<UserCallRecord>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_CALL_RECORD_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserCallRecord>>() {
//                    @Override
//                    public void onNext(APPData<UserCallRecord> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("sys060002")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * app浏览记录上报
//     *
//     * @param dataBack
//     */
//    public void getUserBrowseRecordCreate(final DataBack dataBack) {
//        Type type = new TypeToken<APPData<UserBrowseRecord>>(){}.getType();
//        myParams.put("regionCode",SharedTools.getStringValue(myActivity, "adCode",""));
//        myParams.put("longitude",SharedTools.getStringValue(myActivity, "longitude", ""));
//        myParams.put("latitude",SharedTools.getStringValue(myActivity, "latitude", ""));
//        new HttpDataLoader(myActivity,getHeaderMap(), type ).getHttpData(APIConfig.USER_bROWSE_RECORD_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserBrowseRecord>>() {
//                    @Override
//                    public void onNext(APPData<UserBrowseRecord> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("sys060003")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * APP 异常日志上报
//     *
//     * @param dataBack
//     */
//    public void getLogExceptionCreate(final DataBack dataBack) {
//        Type type = new TypeToken<Map<String,Object>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type ).getHttpData(APIConfig.LOG_EXCEPTION_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<Map<String,Object>>() {
//                    @Override
//                    public void onNext(Map<String,Object> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//
//    }
//    /**
//     * APP 获取短信验证码
//     *
//     * @param dataBack
//     */
//    public void getSendSMS(final DataBack dataBack) {
//        Type type = new TypeToken<SaleManager>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.SEND_SMS,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<SaleManager>() {
//                    @Override
//                    public void onNext(SaleManager data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//
//    }
//
//    /**
//     * APP 轨迹上传
//     *
//     * @param dataBack
//     */
//    public void getDriverUpdatePosition(final DataBack dataBack) {
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type ).getHttpData(APIConfig.DRIVER_UPDATE_POSITION,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * APP 自动更新
//     *
//     * @param dataBack
//     */
//    public void getAppVesion(final DataBack dataBack) {
//        Type type = new TypeToken<APPData<UpdateApp>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type ).getHttpData(APIConfig.GET_APP_VESION,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UpdateApp>>() {
//                    @Override
//                    public void onNext(APPData<UpdateApp> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("sys060002")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//
//    }
//
//    /**
//     *  app初始化接口请求
//     * @param dataBack
//     */
//    public void getDictionary(final DataBack dataBack){
//        Type type = new TypeToken<List<Dictionary>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type ).getHttpData(APIConfig.GET_DICTIONARY,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<Dictionary>>() {
//                    @Override
//                    public void onNext(List<Dictionary> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  广告接口
//     * @param dataBack
//     */
//    public void getAdvertisement(final DataBack dataBack){
//        Type type = new TypeToken<Advertisement>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type ).getHttpData(APIConfig.GET_ADVERTISEMENT,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<Advertisement>() {
//                    @Override
//                    public void onNext(Advertisement data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  首页搜索
//     * @param dataBack
//     */
//    public void getHomeSearch(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<Map<String, Object>>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(), type ).getHttpData(APIConfig.HOME_SEARCH,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<Map<String, Object>>>>() {
//                    @Override
//                    public void onNext(List<APPData<Map<String, Object>>> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  首页接口数据请求
//     * @param dataBack
//     */
//    public void getHome(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<Map<String, Object>>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.GET_HOME_DATA,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<Map<String, Object>>>>() {
//                    @Override
//                    public void onNext(List<APPData<Map<String, Object>>> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        HomeData appModel = new HomeData().getHomeData(data);
//                        if (dataBack != null){
//                            dataBack.getData(appModel);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示
//    }
//
//    /**
//     *  资讯列表数据请求
//     * @param dataBack
//     */
//    public void getArticleList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<Article>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.ARTICLE_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<Article>>() {
//                    @Override
//                    public void onNext(APPDataList<Article> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("operate120003")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  订阅货运列表数据
//     * @param dataBack
//     */
//    public void getTransportSubscribeList(final DataBack dataBack){
//        Type type = new TypeToken<List<APPDataList<RouteEntity>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.TRANSPORT_SUBSCRIBE_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPDataList<RouteEntity>>>() {
//                    @Override
//                    public void onNext(List<APPDataList<RouteEntity>> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  添加订阅货运
//     * @param dataBack
//     */
//    public void getTransportSubscribeCreate(final DataBack dataBack){
//        Type type = new TypeToken<APPData<RouteEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.TRANSPORT_SUBSCRIBE_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber<>(new SubscriberOnNextListener<APPData<RouteEntity>>() {
//                    @Override
//                    public void onNext(APPData<RouteEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal090007")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  删除订阅货运
//     * @param dataBack
//     */
//    public void getTransportSubscribeDelete(final DataBack dataBack){
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.TRANSPORT_SUBSCRIBE_DELETE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData>() {
//                    @Override
//                    public void onNext(APPData data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal090008")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  货运列表数据请求
//     * @param dataBack
//     */
//    public void getCoalTransportList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<TransportMode>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_TRANSPORT_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<TransportMode>>() {
//                    @Override
//                    public void onNext(APPDataList<TransportMode> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal090001")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  货运详情
//     * @param dataBack
//     */
//    public void getCoalTransportInfo(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<MineDynamic>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_TRANSPORT_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<MineDynamic>>>() {
//                    @Override
//                    public void onNext(List<APPData<MineDynamic>> dataList) {
//                        if (dataList == null){
//                            if (dataBack != null){
//                                dataBack.getData(dataList);
//                            }
//                            return;
//                        }
//                        List<TransportMode> dataS = new ArrayList<TransportMode>();
//                        for (APPData<MineDynamic> data : dataList){
//                            if (data.getDataType().equals("coal090002")){
//                                dataS.add(0,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal020004")){
//                                dataS.add(1,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal010004")){
//                                dataS.add(2,data.getEntity());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(dataS);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  货运抢单
//     * @param dataBack
//     */
//    public void getUserTransportOrderCreate(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<TransportMode>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_TRANSPORT_ORDER_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<TransportMode>>>() {
//                    @Override
//                    public void onNext(List<APPData<TransportMode>> dataList) {
//                        if (dataList == null){
//                            if (dataBack != null){
//                                dataBack.getData(dataList);
//                            }
//                            return;
//                        }
//                        List<TransportMode> dataS = new ArrayList<TransportMode>();
//                        for (APPData<TransportMode> data : dataList){
//                            if (data.getDataType().equals("coal090004")){
//                                dataS.add(0,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal020009")){
//                                dataS.add(1,data.getEntity());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(dataS);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  煤炭列表数据请求
//     * @param dataBack
//     */
//    public void getCoalGoodsList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<Coal>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_GOODS_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<Coal>>() {
//                    @Override
//                    public void onNext(APPDataList<Coal> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal070001")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  煤炭详情
//     * @param dataBack
//     */
//    public void getCoalGoodsInfo(final DataBack dataBack){
//        Type type = new TypeToken<APPData<Coal>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_GOODS_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<Coal>>() {
//                    @Override
//                    public void onNext(APPData<Coal> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal070002")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  下订单
//     * @param dataBack
//     */
//    public void getUserCoalGoodsCreate(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<Orders>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COAL_ORDER_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<Orders>>>() {
//                    @Override
//                    public void onNext(List<APPData<Orders>> dataList) {
//                        if (dataList == null){
//                            if (dataBack != null){
//                                dataBack.getData(dataList);
//                            }
//                            return;
//                        }
//                        List<Orders> dataS = new ArrayList<Orders>();
//                        for (APPData<Orders> data : dataList){
//                            if (data.getDataType().equals("coal080002")){
//                                dataS.add(0,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal020008")){
//                                dataS.add(1,data.getEntity());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(dataS);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  信息部列表请求
//     * @param dataBack
//     */
//    public void getCoalSalesList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<InformationDepartment>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_SALES_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<InformationDepartment>>() {
//                    @Override
//                    public void onNext(APPDataList<InformationDepartment> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal020002")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  信息部煤炭列表请求
//     * @param dataBack
//     */
//    public void getCoalSalesCoalGoodsList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<Coal>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_SALES_COAL_GOODS_LIST,myParams,true).subscribe(
//                new ProgressSubscriber<APPDataList<Coal>>(new SubscriberOnNextListener<APPDataList<Coal>>() {
//                    @Override
//                    public void onNext(APPDataList<Coal> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal070003")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  信息部货运列表请求
//     * @param dataBack
//     */
//    public void getCoalSalesCoalTransportList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<TransportMode>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_SALES_COAL_TRANSPORT_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<TransportMode>>() {
//                    @Override
//                    public void onNext(APPDataList<TransportMode> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal090003")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  信息部详情请求
//     * @param dataBack
//     */
//    public void getCoalSalesInfo(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<Map<String, Object>>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_SALES_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<Map<String, Object>>>>() {
//                    @Override
//                    public void onNext(List<APPData<Map<String, Object>>> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        InformationDepartment myListData = new InformationDepartment().getInformationDepartmentInfoData(data);
//                        if (dataBack != null){
//                            dataBack.getData(myListData);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  发现列表请求
//     * @param dataBack
//     */
//    public void getActivityList(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<Map<String, Object>>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.ACTIVITY_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<Map<String, Object>>>>() {
//                    @Override
//                    public void onNext(List<APPData<Map<String, Object>>> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        ActivityData activityData = new ActivityData().getActivityData(data);
//                        if (dataBack != null){
//                            dataBack.getData(activityData);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  地图列表请求
//     * @param dataBack
//     */
//    public void getDiscoverList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<Discover>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.DISCOVER_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<Discover>>() {
//                    @Override
//                    public void onNext(APPDataList<Discover> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("operate120005")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  矿口列表请求
//     * @param dataBack
//     */
//    public void getCompaniesList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<MineMouth>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_COMPANIES_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<MineMouth>>() {
//                    @Override
//                    public void onNext(APPDataList<MineMouth> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        String dataType = data.getDataType();
//                        if (dataType.equals("coal010001")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//
//    /**
//     *  矿口详情请求
//     * @param dataBack
//     */
//    public void getCompaniesInfo(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<Map<String, Object>>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_COMPANIES_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<Map<String, Object>>>>() {
//                    @Override
//                    public void onNext(List<APPData<Map<String, Object>>> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        MineMouth myListData = new MineMouth().getMineMouthData(data);
//                        if (dataBack != null){
//                            dataBack.getData(myListData);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  矿口动态发布
//     * @param dataBack
//     */
//    public void getCoalCompanyRealtimeInfoCreate(List<ImageItem> tempSelectBitmap, final DataBack dataBack){
//        Type type = new TypeToken<APPData<MineDynamic>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).upLoadFiles(APIConfig.COAL_COMPANY_REALIIME_INFO_CREATE,tempSelectBitmap,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<MineDynamic>>() {
//                    @Override
//                    public void onNext(APPData<MineDynamic> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("coal010004")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  矿口动态列表请求
//     * @param dataBack
//     */
//    public void getCoalCompanyRealtimeInfoList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<MineDynamic>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.COAL_COMPANY_REALIIME_INFO_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<MineDynamic>>() {
//                    @Override
//                    public void onNext(APPDataList<MineDynamic> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("coal010003")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getList());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  设置密码
//     * @param dataBack
//     */
//    public void getUserRegister(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_REGISTER,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  更新个人数据
//     * @param dataBack
//     */
//    public void getUserInfo(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  修改个人数据
//     * @param dataBack
//     */
//    public void getUserUpdata(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_UPDATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  用户登录/验证密码
//     * @param dataBack
//     */
//    public void getUserLogin(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_LOGIN,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  注销登录
//     * @param dataBack
//     */
//    public void getUserLogout(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_LOGOUT,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030002")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  修改密码
//     * @param dataBack
//     */
//    public void getUserModifyPassword(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_MODIFY_PASSWORD,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  执行第三方后台登陆或查询
//     * @param dataBack
//     */
//    public void getUserThirdPartyLogin(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_THIRD_PARTY_LOGIN,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  执行第三方后台注册并绑定手机号
//     * @param dataBack
//     */
//    public void getUserThirdPartyBinding(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_THIRD_PARTY_BINDING,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  第三方微信联网
//     * @param dataBack
//     */
//    public void getUserThirdParty(String url, final DataBack dataBack){
//        Type type = new TypeToken<String>(){}.getType();
//        new HttpDataLoader(myActivity,webUrl,type).getHttpData(url,myParams,false).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<String>() {
//                    @Override
//                    public void onNext(String data) {
//                        if (data == null){
//                            return;
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  我的订单列表
//     * @param dataBack
//     */
//    public void getUserCoalOrderList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<Orders>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COAL_ORDER_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<Orders>>() {
//                    @Override
//                    public void onNext(APPDataList<Orders> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("coal080001")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  我的订单详情
//     * @param dataBack
//     */
//    public void getUserCoalOrderInfo(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<Orders>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COAL_ORDER_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<Orders>>>() {
//                    @Override
//                    public void onNext(List<APPData<Orders>> dataList) {
//                        if (dataList == null){
//                            if (dataBack != null){
//                                dataBack.getData(dataList);
//                            }
//                            return;
//                        }
//                        List<Orders> dataS = new ArrayList<Orders>();
//                        for (APPData<Orders> data : dataList){
//                            if (data.getDataType().equals("coal080002")){
//                                dataS.add(0,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal020007")){
//                                dataS.add(1,data.getEntity());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(dataS);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//
//
//    /**
//     *  我的货运单列表
//     * @param dataBack
//     */
//    public void getUserTransportOrderList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<MineDynamic>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_TRANSPOR_ORDER_LIST,myParams,true).subscribe(
//                new ProgressSubscriber<APPDataList<TransportMode>>(new SubscriberOnNextListener<APPDataList<TransportMode>>() {
//                    @Override
//                    public void onNext(APPDataList<TransportMode> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("coal090003")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  我的货运单详情
//     * @param dataBack
//     */
//    public void getUserTransportOrderInfo(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<TransportMode>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_TRANSPORT_ORDER_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<TransportMode>>>() {
//                    @Override
//                    public void onNext(List<APPData<TransportMode>> dataList) {
//                        if (dataList == null){
//                            if (dataBack != null){
//                                dataBack.getData(dataList);
//                            }
//                            return;
//                        }
//                        List<TransportMode> dataS = new ArrayList<TransportMode>();
//                        for (APPData<TransportMode> data : dataList){
//                            if (data.getDataType().equals("coal090004")){
//                                dataS.add(0,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal020009")){
//                                dataS.add(1,data.getEntity());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(dataS);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  装货净重更新
//     * @param dataBack
//     */
//    public void getUserTransportOrderCarryWeight(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<TransportMode>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_TRANSPORT_ORDER_CARRY_WEIGHT_UPDATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<TransportMode>>>() {
//                    @Override
//                    public void onNext(List<APPData<TransportMode>> dataList) {
//                        if (dataList == null){
//                            if (dataBack != null){
//                                dataBack.getData(dataList);
//                            }
//                            return;
//                        }
//                        List<TransportMode> dataS = new ArrayList<TransportMode>();
//                        for (APPData<TransportMode> data : dataList){
//                            if (data.getDataType().equals("coal090004")){
//                                dataS.add(0,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal020009")){
//                                dataS.add(1,data.getEntity());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(dataS);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//
//    /**
//     * 我对货运单的操作
//     * @param dataBack
//     */
//    public void getUserTransportOrderHandle(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<TransportMode>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_TRANSPORT_ORDER_HANDLE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<TransportMode>>>() {
//                    @Override
//                    public void onNext(List<APPData<TransportMode>> dataList) {
//                        if (dataList == null){
//                            if (dataBack != null){
//                                dataBack.getData(dataList);
//                            }
//                            return;
//                        }
//                        List<TransportMode> dataS = new ArrayList<TransportMode>();
//                        for (APPData<TransportMode> data : dataList){
//                            if (data.getDataType().equals("coal090004")){
//                                dataS.add(0,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal020009")){
//                                dataS.add(1,data.getEntity());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(dataS);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  关注的信息部列表
//     * @param dataBack
//     */
//    public void getUserCoalSalesList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<InformationDepartment>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COAL_SALES_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<InformationDepartment>>() {
//                    @Override
//                    public void onNext(APPDataList<InformationDepartment> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("coal020006")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  好友列表
//     * @param dataBack
//     */
//    public void getUserFriendList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_FRIEND_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<UserEntity>>() {
//                    @Override
//                    public void onNext(APPDataList<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030001")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  司机列表
//     * @param dataBack
//     */
//    public void getDriverList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.DRIVER_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<UserEntity>>() {
//                    @Override
//                    public void onNext(APPDataList<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user040002")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  用户消息列表
//     * @param dataBack
//     */
//    public void getPushMessageList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<UserMessage<Object>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.PUSH_MESSAGE_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<UserMessage<Object>>>() {
//                    @Override
//                    public void onNext(APPDataList<UserMessage<Object>> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("operate060002")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//
//    /**
//     *  用户消息删除
//     * @param dataBack
//     */
//    public void getPushMessageDelete(final DataBack dataBack){
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.PUSH_MESSAGE_DELETE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData>() {
//                    @Override
//                    public void onNext(APPData data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("operate060003")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  添加好友
//     * @param dataBack
//     */
//    public void getUserFriendCreate(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_FRIEND_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030002")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  删除好友
//     * @param dataBack
//     */
//    public void getUserFriendDelete(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_FRIEND_DELETE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030002")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  采购预约
//     * @param dataBack
//     */
//    public void getUserDemandList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<UserDemand>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_DEMAND_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<UserDemand>>() {
//                    @Override
//                    public void onNext(APPDataList<UserDemand> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("coal130001")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getList());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  采购预约详情
//     * @param dataBack
//     */
//    public void getUserDemandInfo(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserDemand>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_DEMAND_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserDemand>>() {
//                    @Override
//                    public void onNext(APPData<UserDemand> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("coal130003")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  添加采购预约
//     * @param dataBack
//     */
//    public void getUserDemandCreate(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserDemand>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_DEMAND_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserDemand>>() {
//                    @Override
//                    public void onNext(APPData<UserDemand> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("coal130002")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  删除采购预约
//     * @param dataBack
//     */
//    public void getUserDemandDelete(final DataBack dataBack){
//        Type type = new TypeToken<APPData<APPData>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_DEMAND_DELETE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<APPData>>() {
//                    @Override
//                    public void onNext(APPData<APPData> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        try {
//                            if (data.getDataType().equals("coal130004")){
//                                if (dataBack != null){
//                                    dataBack.getData(data.getMessage());
//                                }
//                            }
//                        } catch (Exception e) {
//                            if (dataBack != null){
//                                dataBack.getData("");
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  关注信息部
//     * @param dataBack
//     */
//    public void getUserCoalSalesCreate(final DataBack dataBack){
//        Type type = new TypeToken<APPData<InformationDepartment>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COAL_SALES_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<InformationDepartment>>() {
//                    @Override
//                    public void onNext(APPData<InformationDepartment> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030002")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  取消关注信息部
//     * @param dataBack
//     */
//    public void getUserCoalSalesDelete(final DataBack dataBack){
//        Type type = new TypeToken<APPData<InformationDepartment>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COAL_SALES_DELETE,myParams,true).subscribe(
//                new ProgressSubscriber<>(new SubscriberOnNextListener<APPData<InformationDepartment>>() {
//                    @Override
//                    public void onNext(APPData<InformationDepartment> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030002")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  提交实名认证资料
//     * @param dataBack
//     */
//    public void getUserRealnameAuthCreate(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserAuthenticationEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_REALNAME_AUTH_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030003")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  获取实名认证资料
//     * @param dataBack
//     */
//    public void getUserRealnameAuthInfo(final DataBack dataBack){
//        Type type = new TypeToken<APPData<UserAuthenticationEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_REALNAME_AUTH_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserAuthenticationEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserAuthenticationEntity> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030003")){
//                            if (dataBack != null){
//                                dataBack.getData(data.getEntity());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  提交司机认证资料
//     * @param dataBack
//     */
//    public void getUserDriverAuthCreate(final DataBack dataBack){
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_DRIVER_AUTH_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData>() {
//                    @Override
//                    public void onNext(APPData data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (data.getDataType().equals("user030006")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//
//    /**
//     *  获取司机认证资料
//     * @param dataBack
//     */
//    public void getUserDriverAuthInfo(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<UserAuthenticationEntity>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_DRIVER_AUTH_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<UserAuthenticationEntity>>>() {
//                    @Override
//                    public void onNext(List<APPData<UserAuthenticationEntity>> data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        APPData<UserAuthenticationEntity> appData = new APPData<UserAuthenticationEntity>();
//                        for (APPData<UserAuthenticationEntity> appData1 : data){
//                            if (appData1.getDataType().equals("user030004")){
//                                appData.setEntity(appData1.getEntity());
//                            }
//                            if (appData1.getDataType().equals("user030010")){
//                                appData.setList(appData1.getList());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(appData);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * 添加/编辑车辆信息
//     * @param dataBack
//     */
//    public void getUserVehiclesCreate(final DataBack dataBack){
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_VEHICLES_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData>() {
//                    @Override
//                    public void onNext(APPData data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     * 司机登记信息
//     * @param dataBack
//     */
//    public void getUserDriverRegister(final DataBack dataBack){
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_DRIVER_REGISTER,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData>() {
//                    @Override
//                    public void onNext(APPData data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * 图片上传
//     * @param dataBack
//     */
//    public void getPictureUpload(String myParamsString, final DataBack dataBack) throws Exception {
//        Type type = new TypeToken<APPMessage>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).upLoadFile(APIConfig.USER_PICTURE_UPLOAD,myParamsString,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPMessage>() {
//                    @Override
//                    public void onNext(APPMessage data) {
//                        if (data == null){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                            return;
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
////                        if (data.getDataType().equals("user030001")){  //修改头像
////                            if (dataBack != null){
////                                dataBack.getData(data);
////                            }
////                        }else if (data.getDataType().equals("user030003")){ //实名认证
////                            if (dataBack != null){
////                                dataBack.getData(data);
////                            }
////                        }else if (data.getDataType().equals("user030004")){ //司机认证
////                            if (dataBack != null){
////                                dataBack.getData(data);
////                            }
////                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * 榜单图片上传
//     * @param dataBack
//     */
//    public void getPictureUploadList(String myParamsString, final DataBack dataBack) throws Exception {
//        Type type = new TypeToken<List<APPData<TransportMode>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type)
//                .upLoadFile(APIConfig.USER_TRANSPORT_ORDER_WEIGHT_DOC_PIC_UPLOAD,myParamsString,myParams,true)
//                .subscribe(new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<TransportMode>>>() {
//                    @Override
//                    public void onNext(List<APPData<TransportMode>> datalist) {
//                        if (datalist == null){
//                            if (dataBack != null){
//                                dataBack.getData(datalist);
//                            }
//                            return;
//                        }
//                        List<TransportMode> dataS = new ArrayList<TransportMode>();
//                        for (APPData<TransportMode> data : datalist){
//                            if (data.getDataType().equals("coal090004")){ //货运单信息
//                                dataS.add(0,data.getEntity());
//                            }
//                            if (data.getDataType().equals("coal020009")){ //信息部信息
//                                dataS.add(1,data.getEntity());
//                            }
//                        }
//                        if (dataBack != null){
//                            dataBack.getData(dataS);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//
//    /**
//     *  取消支付
//     * @param dataBack
//     */
//    public void getConsultingFeeRecordCancel(final DataBack dataBack){
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.CONSULTINT_FEE_RECORD_CANCEL,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData>() {
//                    @Override
//                    public void onNext(APPData data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  资讯费支付信息获取
//     * @param dataBack
//     */
//    public void getConsultingFeeRecordPrepaid(final DataBack dataBack){
//        Type type = new TypeToken<PayMent>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.CONSULTINT_FEE_RECORD_PREPAID,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<PayMent>() {
//                    @Override
//                    public void onNext(PayMent data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  调后台接口生成预支付订单
//     * @param dataBack
//     */
//    public void setUserWeChatPay(final DataBack dataBack){
//        Type type = new TypeToken<PayMent>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.CONSULTINT_FEE_RECORD_CREATE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<PayMent>() {
//                    @Override
//                    public void onNext(PayMent data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  获取支付订单列表
//     * @param dataBack
//     */
//    public void getConsultingFeeRecordList(final DataBack dataBack){
//        Type type = new TypeToken<APPDataList<PayMentOrder>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.CONSULTINT_FEE_RECORD_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPDataList<PayMentOrder>>() {
//                    @Override
//                    public void onNext(APPDataList<PayMentOrder> data) {
//                        if (data.getDataType().equals("operate060009")){
//                            if (dataBack != null){
//                                dataBack.getData(data);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,false));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  获取支付订单详情
//     * @param dataBack
//     */
//    public void getConsultingFeeRecordInfo(final DataBack dataBack){
//        Type type = new TypeToken<List<APPData<Object>>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.CONSULTINT_FEE_RECORD_INFO,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<List<APPData<Object>>>() {
//                    @Override
//                    public void onNext(List<APPData<Object>> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  获取我的优惠卷
//     * @param dataBack
//     */
//    public void getUserCouponList(final DataBack dataBack){
//        Type type = new TypeToken<APPData<CouponsEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COUPON_LIST,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<CouponsEntity>>() {
//                    @Override
//                    public void onNext(APPData<CouponsEntity> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  获取更多优惠卷
//     * @param dataBack
//     */
//    public void getUserCouponMore(final DataBack dataBack){
//        Type type = new TypeToken<APPData<CouponsEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COUPON_MORE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<CouponsEntity>>() {
//                    @Override
//                    public void onNext(APPData<CouponsEntity> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  领取优惠卷
//     * @param dataBack
//     */
//    public void getUserCouponObtainType(final DataBack dataBack){
//        Type type = new TypeToken<APPData<CouponsEntity>>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COUPON_OBTAIN,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<CouponsEntity>>() {
//                    @Override
//                    public void onNext(APPData<CouponsEntity> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  优惠卷支付
//     * @param dataBack
//     */
//    public void getConsultingFeeRecordCouponPayment(final DataBack dataBack){
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.CONSULTING_FEE_RECORD_COUPON_PAYMENT,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData>() {
//                    @Override
//                    public void onNext(APPData data) {
//                        if (dataBack != null){
//                            dataBack.getData(data.getMessage());
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *  免密码登录
//     * @param dataBack
//     */
//    public void getUserAvoidLogin(final DataBack dataBack) {
//        Type type = new TypeToken<APPData<UserEntity>>() {
//        }.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(), type).getHttpData(APIConfig.USER_AVOID_LOGIN, myParams, true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<UserEntity>>() {
//                    @Override
//                    public void onNext(APPData<UserEntity> data) {
//                        if (data.getDataType().equals("user030001"))
//                            if (dataBack != null) {
//                                dataBack.getData(data.getEntity());
//                            }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败", e.toString());
//                        if (dataBack != null) {
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity, true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//    /**
//     *  用户留言/反馈
//     * @param dataBack
//     */
//    public void commitContent(final DataBack dataBack){
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.USER_COMMIT_MESSAGE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<Object>>() {
//                    @Override
//                    public void onNext(APPData<Object> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * 删除车辆信息
//     *
//     * @param dataBack
//     */
//    public void deleteVehicle(final DataBack dataBack) {
//        Type type = new TypeToken<APPData>() {}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(), type).getHttpData(APIConfig.DELETE_VEHICLE_INFO, myParams, true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<Object>>() {
//                    @Override
//                    public void onNext(APPData<Object> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败", e.toString());
//                        if (dataBack != null) {
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity, true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     *启用车辆
//     *
//     * @param dataBack
//     */
//    public void startVehicle(final DataBack dataBack) {
//        Type type = new TypeToken<APPData>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(APIConfig.START_VEHICLE,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<APPData<Object>>() {
//                    @Override
//                    public void onNext(APPData<Object> data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                        if (dataBack != null){
//                            dataBack.getError(e);
//                        }
//                    }
//                }, myActivity,true));   //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//
//    /**
//     *  自定义接口 GET 网络请求
//     * @param url
//     * @param dataBack
//     */
//    public void getHttpData(String url, Type type, final DataBack dataBack) {
//        new HttpDataLoader(myActivity,getHeaderMap(),type).getHttpData(url,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<Object>() {
//                    @Override
//                    public void onNext(Object data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        GHLog.e("联网失败",e.toString());
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//    /**
//     * 自定义接口 POST 网络请求
//     *
//     * @param dataBack
//     */
//    public void postHttpData(String URL, final DataBack dataBack) {
//        Type type = new TypeToken<JSONObject>(){}.getType();
//        new HttpDataLoader(myActivity,getHeaderMap(),type).postHttpData(URL,myParams,true).subscribe(
//                new ProgressSubscriber(new SubscriberOnNextListener<JSONObject>() {
//                    @Override
//                    public void onNext(JSONObject data) {
//                        if (dataBack != null){
//                            dataBack.getData(data);
//                        }
//                    }
//                }, myActivity,true));  //最后一个参数表示是否显示等待条，true 表示显示，
//    }
//
//
//    /**
//     * 在消息请求头中添加 公共参数
//     * @return
//     */
//    private Map<String,String> getHeaderMap(){
//        Map<String,String> headerMap = new HashMap<>();
//        headerMap.put("deviceId", Build.BRAND);  //设备型号
//        headerMap.put("machineCode", BaseUtils.GetDeviceID(myActivity));  //机器码
//        headerMap.put("platform","android");  //设备类型
//        headerMap.put("appName","lmb");  //应用名称
//        try {
//            headerMap.put("appVersion",new BaseUtils().getVersionName(myActivity));   //当前应用版本号
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        headerMap.put("platformVersion", Build.VERSION.RELEASE);         //当前设备版本号
//        headerMap.put("channel",new BaseUtils().getAppMetaData(myActivity,new Config().CHANNEL_KEY)); //获取打包渠道名称
//        headerMap.put("apiVersion", APIConfig.API_VERSION);   //协议版本号
//        if(!StringUtils.isEmpty(((TelephonyManager) (myActivity.getSystemService(myActivity.TELEPHONY_SERVICE))).getDeviceId())) {
//            headerMap.put("imei",((TelephonyManager) (myActivity.getSystemService(myActivity.TELEPHONY_SERVICE))).getDeviceId());   //获取当前手机唯一标识
//        }
//        String userId = SharedTools.getStringValue(myActivity,"userId","-1");
//        if(!userId.equals("-1")){
//            headerMap.put("userId",userId);  //用户id
//        }
//        String cityId = SharedTools.getStringValue(myActivity,"cityId","-1");
//        if(!userId.equals("-1")){
//            headerMap.put("cityId",cityId); //城市id
//        }
//        return headerMap;
//    }

}
