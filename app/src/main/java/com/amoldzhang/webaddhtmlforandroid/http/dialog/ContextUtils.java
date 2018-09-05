package com.amoldzhang.webaddhtmlforandroid.http.dialog;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;


public class ContextUtils {
    /**
     * 对于一个没有被载入或者想要动态载入的界面, 都需要使用inflate来载入
     *
     * @param context
     * @return
     */
    public static LayoutInflater getLayoutInflater(Context context) {
        return (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

}
