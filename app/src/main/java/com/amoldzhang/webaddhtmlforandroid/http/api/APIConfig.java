package com.amoldzhang.webaddhtmlforandroid.http.api;

/**
 *  接口访问文件配置
 * Created by amoldZhang on 2017/7/18.
 */
public class APIConfig {


    /**
     * 接口加密验校
     * API_VERSION  接口版本号
     *  ACCESS_KEY  acessKey 验校码（来自后台生成）
     */
    public static final String API_VERSION  = "4.0.4";
//    public static final String ACCESS_KEY  = "163da318-b91d-4c97-9c9a-803e0703aa1e"; //3.5 发布
//     public static final String ACCESS_KEY  = "8c2a61a9-0140-4246-912e-7999c413e6b9"; //3.6  发布

//    public static final String ACCESS_KEY  = "c997de29-a16b-47d6-9ac7-1d082d4d8b28"; //3.5.8  外网测试 apitest
     public static final String ACCESS_KEY  = "af19fb80-0459-4325-bc6b-cdbddf6b9c3d"; //3.6.4  ----   3.6.9 外网测试


      //TODO 配置网络访问接口
    public static final String IP = "http://lameiapi.sxhalo.com";//115.28.114.135   //发布时 接口外网地址

//    public static final String IP = "http://172.16.99.116:8080";   //本地测
//    public static final String IP = "http://172.16.99.131:8080";   //本地测
//    public static final String IP = "http://172.16.99.199:8080";  //本地测
//    public static final String IP = "http://172.16.99.195:8080";   //本地测
//    public static final String IP = "http://172.16.99.172:8080";   //本地测

//    public static final String SERVICE = "/lamapi/";
        public static final String SERVICE = "/lamtest/";
    public static final String WEB_SERVICE = IP + SERVICE;
//    public static final String WEB_SERVICE = BuildConfig.WEB_API;



    /**
     * 图片上传时 固定常量
     *  头像：headPic
     *  身份证正：identitycardFrontPic
     *  身份证反：identitycardBackPic
     *  驾驶证：driverLicensePic
     *  行驶证：vehicleLicenseHomePic
     *  行驶证附页：vehicleLicenseAttachedPic
     *  人车合照：carPhotoPic
     */
    public static final String HEAD_PIC  = "headPic"; //用户头像
    public static final String IDENTITY_FRONT_PIC  = "identitycardFrontPic"; //身份证正面
    public static final String IDENTITY_BACK_PIC  = "identitycardBackPic"; //身份证反面
    public static final String DRIVER_PIC  = "driverLicensePic"; //驾驶证
    public static final String VEHICLE_HOME_PIC  = "vehicleLicenseHomePic"; //行驶证
    public static final String VEHICLE_ATTACHED_PIC  = "vehicleLicenseAttachedPic"; //行驶证附页
    public static final String CAR_PHOTO_PIC  = "carPhotoPic"; //人车合照


    /**
     * app 消息红点提示
     */
    public static final String PUSH_MESSAGE_UNREAD_COUNT = "pushMessage/unreadCount";

    /**
     * 消息已读标示
     */
    public static final String PUSH_MESSAGE_READ_MESSAGE = "pushMessage/readMessage";

    /**
     * app电话上报接口
     */
    public static final String USER_CALL_RECORD_CREATE = "userCallRecord/create";

    /**
     * app浏览记录上报
     */
    public static final String USER_bROWSE_RECORD_CREATE = "userBrowseRecord/create";

    /**
     * app 异常上报
     */
    public static final String LOG_EXCEPTION_CREATE = "logException/create";

    /**
     * 获取短信验证码
     */
    public static final String SEND_SMS = "sendSMS";

    /**
     * app轨迹更新
     */
    public static final String DRIVER_UPDATE_POSITION = "driver/update/position";  // APPData<UserEntity>

    /**
     * app自动更新
     */
    public static final String GET_APP_VESION = "getAppVesion"; // APPData<UpdateApp>

    /**
     * 获取系统数据接口   URL: http://172.16.99.116:8080/lamapi/getDictionary
     */
    public static final String GET_DICTIONARY = "getDictionary";

    /**
     * 广告接口   URL: http://172.16.99.116:8080/lamapi/getAdvertisement
     */
    public static final String GET_ADVERTISEMENT = "getAdvertisement";

    /**
     * 首页搜索接口
     */
    public static final String HOME_SEARCH = "homeSearch";

    /**
     * app首页接口   URL: http://172.16.99.116:8080/lamapi/getHomeData
     */
    public static final String GET_HOME_DATA = "getHomeData";

    /**
     * 资讯列表请求  URL: http://172.16.99.116:8080/lamapi/article/list
     */
    public static final String ARTICLE_LIST = "article/list";

    /**
     * 订阅货运列表
     */
    public static final String TRANSPORT_SUBSCRIBE_LIST = "transportSubscribe/list";

    /**
     * 添加订阅货运
     */
    public static final String TRANSPORT_SUBSCRIBE_CREATE = "transportSubscribe/create";

    /**
     * 删除订阅货运
     */
    public static final String TRANSPORT_SUBSCRIBE_DELETE = "transportSubscribe/delete";

    /**
     * 货运列表请求  URL: http://172.16.99.116:8080/lamapi/coalGoods/list
     */
    public static final String COAL_TRANSPORT_LIST = "coalTransport/list";

    /**
     * 货运详情请求  URL: http://172.16.99.116:8080/lamapi/coalGoods/list
     */
    public static final String COAL_TRANSPORT_INFO = "coalTransport/info";

    /**
     * 货运抢单  URL: http://172.16.99.116:8080/lamapi/coalGoods/list
     */
    public static final String USER_TRANSPORT_ORDER_CREATE = "user/transportOrder/create";


    /**
     * 煤炭列表请求  URL: http://172.16.99.116:8080/lamapi/coalGoods/list
     */
    public static final String COAL_GOODS_LIST = "coalGoods/list";

    /**
     * 煤炭详情  URL: http://172.16.99.116:8080/lamapi/coalGoods/info
     */
    public static final String COAL_GOODS_INFO = "coalGoods/info";

    /**
     * 下预约单
     */
    public static final String USER_COAL_ORDER_CREATE = "user/coalOrder/create";

    /**
     * 信息部列表
     */
    public static final String COAL_SALES_LIST = "coalSales/list";

    /**
     * 信息部煤炭列表
     */
    public static final String COAL_SALES_COAL_GOODS_LIST = "coalSales/coalGoods/list";

    /**
     * 信息部货运列表
     */
    public static final String COAL_SALES_COAL_TRANSPORT_LIST = "coalSales/coalTransport/list";

    /**
     * 信息部详情
     */
    public static final String COAL_SALES_INFO = "coalSales/info";

    /**
     * 发现列表
     */
    public static final String ACTIVITY_LIST = "activity/list";

    /**
     * 地图列表
     */
    public static final String DISCOVER_LIST = "discover/list";

    /**
     * 矿口列表
     */
    public static final String COAL_COMPANIES_LIST = "coalCompanies/list";

    /**
     * 矿口详情
     */
    public static final String COAL_COMPANIES_INFO = "coalCompanies/info";


    /**
     * 矿口动态发布（有图）
     */
    public static final String COAL_COMPANY_REALIIME_INFO_CREATE = "coalCompanyRealtimeInfo/create";

    /**
     *  矿口动态列表请求
     */
    public static final String COAL_COMPANY_REALIIME_INFO_LIST = "coalCompanyRealtimeInfo/list";

    /**
     * 注册
     */
    public static final String USER_REGISTER = "user/register";

    /**
     * 更新个人数据
     */
    public static final String USER_INFO = "user/info";

    /**
     * 修改个人数据
     */
    public static final String USER_UPDATE = "user/update";

    /**
     * 用户登录/验证密码
     */
    public static final String USER_LOGIN = "user/login";

    /**
     * 注销登录
     */
    public static final String USER_LOGOUT = "user/logout";


    /**
     * 修改密码
     */
    public static final String USER_MODIFY_PASSWORD = "user/modifyPassword";


    /**
     * 第三方登录
     */
    public static final String USER_THIRD_PARTY_LOGIN = "user/thirdPartyLogin";

    /**
     * 第三方登录 绑定手机号
     */
    public static final String USER_THIRD_PARTY_BINDING = "user/thirdPartyBinding";

    /**
     * 我的订单列表
     */
    public static final String USER_COAL_ORDER_LIST = "user/coalOrder/list";

    /**
     * 我的订单详情
     */
    public static final String USER_COAL_ORDER_INFO = "user/coalOrder/info";

    /**
     * 我的货运单列表
     */
    public static final String USER_TRANSPOR_ORDER_LIST = "user/transportOrder/list";

    /**
     * 我的货运单详情
     */
    public static final String USER_TRANSPORT_ORDER_INFO = "user/transportOrder/info";

    /**
     * 装货净重更新
     */
    public static final String USER_TRANSPORT_ORDER_CARRY_WEIGHT_UPDATE = "user/transportOrder/carryWeight/update";

    /**
     * 我对货运单的处理
     */
    public static final String USER_TRANSPORT_ORDER_HANDLE = "user/transportOrder/handle";

    /**
     * 我的关注信息部列表
     */
    public static final String USER_COAL_SALES_LIST = "user/coalSales/list";

    /**
     * 我的好友列表
     */
    public static final String USER_FRIEND_LIST = "user/friend/list";

    /**
     * 司机列表
     */
    public static final String DRIVER_LIST = "driver/list";

    /**
     * 用户消息列表
     */
    public static final String PUSH_MESSAGE_LIST = "pushMessage/list";


    /**
     * 用户消息删除
     */
    public static final String PUSH_MESSAGE_DELETE = "pushMessage/delete";

    /**
     * 添加好友
     */
    public static final String USER_FRIEND_CREATE = "user/friend/create";

    /**
     * 删除好友
     */
    public static final String USER_FRIEND_DELETE = "user/friend/delete";

    /**
     * 采购预约列表
     */
    public static final String USER_DEMAND_LIST = "userDemand/list";

    /**
     * 采购预约详情
     */
    public static final String USER_DEMAND_INFO = "userDemand/info";

    /**
     * 添加采购预约
     */
    public static final String USER_DEMAND_CREATE = "userDemand/create";

    /**
     * 删除采购预约
     */
    public static final String USER_DEMAND_DELETE = "userDemand/delete";

    /**
     * 关注信息部
     */
    public static final String USER_COAL_SALES_CREATE = "user/coalSales/create";

    /**
     * 取消关注信息部
     */
    public static final String USER_COAL_SALES_DELETE = "user/coalSales/delete";

    /**
     * 提交实名认证信息
     */
    public static final String USER_REALNAME_AUTH_CREATE = "user/realnameAuth/create";

    /**
     * 获取实名认证信息
     */
    public static final String USER_REALNAME_AUTH_INFO = "user/realnameAuth/info";

    /**
     * 提交司机认证信息
     */
    public static final String USER_DRIVER_AUTH_CREATE = "user/driverAuth/create";

    /**
     * 获取司机认证信息
     */
    public static final String USER_DRIVER_AUTH_INFO = "user/driverAuth/info";

    /**
     * 添加车辆信息
     */
    public static final String USER_VEHICLES_CREATE = "user/vehicles/create";
    /**
     * 司机登记信息
     */
    public static final String USER_DRIVER_REGISTER = "user/driver/register";

    /**
     * 图片上传
     */
    public static final String USER_PICTURE_UPLOAD = "user/picture/upload";

    /**
     * 磅单图片上传
     */
    public static final String USER_TRANSPORT_ORDER_WEIGHT_DOC_PIC_UPLOAD = "user/transportOrder/weightDocPic/upload"; // List<APPData<TransportMode>>

    /**
     *  取消支付
     */
    public static final String CONSULTINT_FEE_RECORD_CANCEL = "consultingFeePayRecord/cancel";
    /**
     *  资讯费预支付
     */
    public static final String CONSULTINT_FEE_RECORD_PREPAID = "consultingFeePayRecord/prepaid";
    /**
     *  优惠卷支付
     */
    public static final String CONSULTING_FEE_RECORD_COUPON_PAYMENT = "consultingFeePayRecord/couponPayment";
    /**
     *  获取支付信息
     */
    public static final String CONSULTINT_FEE_RECORD_CREATE = "consultingFeePayRecord/create";
    /**
     *  获取支付订单列表
     */
    public static final String CONSULTINT_FEE_RECORD_LIST = "consultingFeePayRecord/list";
    /**
     *  获取支付订单详情
     */
    public static final String CONSULTINT_FEE_RECORD_INFO = "consultingFeePayRecord/info";
    /**
     *  获取我的优惠卷
     */
    public static final String USER_COUPON_LIST = "userCoupon/list";
    /**
     *  获取更多优惠卷
     */
    public static final String USER_COUPON_MORE = "userCoupon/more";
    /**
     *  领取优惠卷
     */
    public static final String USER_COUPON_OBTAIN = "userCoupon/obtain";
    /**
     *  优惠卷支付
     */
    public static final String USER_AVOID_LOGIN = "user/avoidLogin";
    /**
     *  用户留言/反馈
     */
    public static final String USER_COMMIT_MESSAGE = "userGuestbook/create";
    /**
     *  删除车辆信息
     */
    public static final String DELETE_VEHICLE_INFO = "user/vehicles/delete";
    /**
     *  启用车辆
     */
    public static final String START_VEHICLE= "user/vehicles/enable";


}

