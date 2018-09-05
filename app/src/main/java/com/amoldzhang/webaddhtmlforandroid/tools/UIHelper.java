package com.amoldzhang.webaddhtmlforandroid.tools;

import android.app.Activity;
import android.content.Intent;

import java.util.Map;

/**
 *  界面显示优化，以及跳转工具类
 * Created by amoldZhang on 2018/2/2.
 */
public class UIHelper {

    public final static String TAG = "UIHelper";

    /**
     *  对于快速点击的判断
     * @param lastClickTime 最后一次的点击时间
     * @param longTime   自己定义想要的时间间隔
     * @return 当还在限制时间之内时，反回 0
     *          否则返回最后点击时间，既当前时间
     */
    public static long isFastDoubleClick(int lastClickTime,int longTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= longTime) {
            return 0;
        } else {
            return time;
        }
    }

    /**
     *  跳转指定界面
     * @param myActivity 当前活动界面
     * @param toClass     要去的活动界面
     * @param flage       是否要结束当前界面
     */
    public static void setJump(Activity myActivity,Class toClass, Boolean flage){
        Intent intent = new Intent(myActivity,toClass);
        myActivity.startActivity(intent);
        if (flage){
            myActivity.finish();
        }
    }

}
