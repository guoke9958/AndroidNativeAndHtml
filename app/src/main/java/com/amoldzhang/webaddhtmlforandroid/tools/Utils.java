package com.amoldzhang.webaddhtmlforandroid.tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.view.ViewCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.util.UUID;

/**
 *  app 工具类
 * Created by amoldZhang on 2018/2/3.
 */
public class Utils {

    /**
     * 文件自定义路径
     * @param context
     * @param name
     * @return
     */
    public static String setDbPath(Context context, String name) {
        String packageName = context.getPackageName();
//		String dbPath = String.format("/data/data/%1$s/databases/%2$s", packageName,name);
//		String dbPath = String.format(FileUtils.getFilesDir(context), packageName,name);
        String dbPath = String.format(context.getDatabasePath("database").getPath(), packageName,name); //
        File file = new File(dbPath);
        if (file.exists()) {
            System.out.println("exists"+file.exists());
        } else {
            file.getParentFile().mkdirs();
            if (file.exists()) {
                System.out.println("exists");
            } else {
                System.out.println("not exists");
            }
        }
        return dbPath;
    }

    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /** 获取屏幕的宽度 */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕内容高度
     * @param activity
     * @return
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int result = 0;
        int resourceId = activity.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        int screenHeight = dm.heightPixels - result;
        return screenHeight;
    }

    /**
     * dp转px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /** 获取屏幕的高度 */
    @SuppressWarnings("deprecation")
    public final static int getWindowsHight(Context countext) {
        WindowManager wm = (WindowManager) countext
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /** 获取机器码 */
    public static String GetDeviceID(Context mContext) {
        String uniqueId = "null";
        try {
            final TelephonyManager tm = (TelephonyManager) mContext
                    .getSystemService(Context.TELEPHONY_SERVICE);
            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = ""
                    + Settings.Secure.getString(
                    mContext.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(),
                    ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            uniqueId = deviceUuid.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueId;
    }


//    /***
//     *  设置沉侵式状态栏
//     * @param mActivity
//     * @param color
//     */
//    public static void initStatusView(Activity mActivity,int color) {
//        //5.0 以上直接设置状态栏颜色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mActivity.getWindow().setStatusBarColor(mActivity.getResources().getColor(color));
//        }else {
//            //根布局添加占位状态栏
//            ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
//            View statusBarView = new View(mActivity);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    Utils.getStatusBarHeight(mActivity));
//            statusBarView.setBackgroundColor(mActivity.getResources().getColor(color));
//            decorView.addView(statusBarView, lp);
//
//            Window window = mActivity.getWindow();
//            WindowManager.LayoutParams attributes = window.getAttributes();
//            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            //int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
//            attributes.flags |= flagTranslucentStatus;
////                //attributes.flags |= flagTranslucentNavigation;
//            window.setAttributes(attributes);
//        }
//    }

    /**
     * 根据自定义色值来设置系统状态栏
     * @param mActivity
     * @param color
     */
    public static void initStatusView(Activity mActivity,int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = mActivity.getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(mActivity.getResources().getColor(color));
            ViewGroup mContentView = (ViewGroup) mActivity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Window window = mActivity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = Utils.getStatusBarHeight(mActivity);
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置contentview为fitsSystemWindows
            ViewGroup contentView = (ViewGroup) mActivity.findViewById(android.R.id.content);
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
            //给statusbar着色
            View view = new View(mActivity);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));
            view.setBackgroundColor(mActivity.getResources().getColor(color));
            contentView.addView(view);
        }
    }

    /**
     * 利用反射获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight(Activity mActivity) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mActivity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取当前程序的版本号
     */
    public static String getVersionName(Context context) throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionName;
    }

    /**
     * 判断某个应用是否安装
     * @param activity
     * @param uri
     * @return
     */
    public static boolean isAppInstalled(Activity activity,String uri){
        PackageManager pm = activity.getPackageManager();
        boolean installed = false;
        try{
            pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES);
            installed =true;
        }catch(PackageManager.NameNotFoundException e){
            installed =false;
        }
        return installed;
    }

    /**
     * 获取application中指定的meta-data
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }
}
