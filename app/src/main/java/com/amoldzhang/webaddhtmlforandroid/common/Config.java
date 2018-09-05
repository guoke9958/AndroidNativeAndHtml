package com.amoldzhang.webaddhtmlforandroid.common;

/**
 * Created by amoldZhang on 2016/12/3.
 */
public class Config {
    public static String CHANNEL_KEY = "SXHALO_CHANNEL_NAME"; //多渠道打包参数
    /**数据库版本号*/
    public static int DB_VERSION = 7; // 更新时候只需要修改这里就可以了
    /**数据库名称*/
    public static String DB_NAME = "pullcoaldatabase.db";// 数据库名称
    //AES加密密码
    public static String password = "FC72E7899C804EEC";
    //AES加密偏移量
    public static String IV ="43C0EC5CECAD4396";
    // 0加密   非0 不加密
    public static String AES = "0";

    public static boolean AES_APP = true;

}
