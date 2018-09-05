package com.amoldzhang.webaddhtmlforandroid.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

/**
 *
 * 文件名：SharedPrefsUtil.java App存储数据工具类
 *
 */
public class SharedTools {

	/** 数据存储的XML名称 **/
	public final static String SETTING = "pullcoaltreasure";

	/**
	 * 存储数据(Long)
	 *
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putLongValue(Context context, String key, long value) {
		Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
		sp.putLong(key, value);
		sp.commit();
	}


	/**
	 * 存储数据(Int)
	 *
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putIntValue(Context context, String key, int value) {
		Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
		sp.putInt(key, value);
		sp.commit();
	}

	/**
	 * 存储数据(String)
	 *
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putStringValue(Context context, String key, String value) {
		Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE)
				.edit();
		sp.putString(key, value);
		sp.commit();
	}

	/**
	 * 存储数据(boolean)
	 *
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putBooleanValue(Context context, String key,
                                       boolean value) {
		Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE)
				.edit();
		sp.putBoolean(key, value);
		sp.commit();
	}


	/**
	 * 取出数据（Long）
	 *
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	@SuppressLint("NewApi")
	public static Set<String> getSetValue(Context context, String key, Set<String> defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTING,
				Context.MODE_PRIVATE);
		Set<String> value = sp.getStringSet(key, defValue);
		return value;
	}

	/**
	 * 取出数据（int）
	 *
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getIntValue(Context context, String key, int defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTING,
				Context.MODE_PRIVATE);
		int value = sp.getInt(key, defValue);
		return value;
	}

	/**
	 * 取出数据（boolean）
	 *
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBooleanValue(Context context, String key,
                                          boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTING,
				Context.MODE_PRIVATE);
		boolean value = sp.getBoolean(key, defValue);
		return value;
	}

	/**
	 * 取出数据（String）
	 *
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getStringValue(Context context, String key,
                                        String defValue) {
		SharedPreferences sp = context.getSharedPreferences(SETTING,
				Context.MODE_PRIVATE);
		String value = sp.getString(key, defValue);
		return value;
	}

	/**
	 * 清空所有数据
	 *
	 * @param context
	 * @return
	 */
	public static void clear(Context context) {
		Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE)
				.edit();
		sp.clear();
		sp.commit();
	}

	/**
	 * 清空所有数据
	 *
	 * @param context
	 * @param key
	 * @return
	 */
	public static void remove(Context context, String key) {
		Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE)
				.edit();
		sp.remove(key);
		sp.commit();
	}
}