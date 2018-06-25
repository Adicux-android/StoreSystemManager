package com.gang.store.storesystemmanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/6/24.
 */

public class ShareUtils {
    public static final String NAME = "com.gang.store.storesystemmanager";
    /** * 存入数据(String) */
    public static void putString(Context context, String key, String value)
    {
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        spf.edit().putString(key, value).apply(); }
    /** * 取数据(上下文，钥匙，未取得数据返回的默认值)(String) */
    public static String getString(Context context, String key, String value)
    {
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return spf.getString(key, value); }
    /** * 存入数据(Int) */
    public static void putInt(Context context, String key, int value)
    {
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        spf.edit().putInt(key, value).apply(); }
    /** * 取数据(上下文，钥匙，未取得数据返回的默认值)(Int) */
    public static int getInt(Context context, String key, int value)
    {
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return spf.getInt(key, value); } /** * 存入数据(Int) */
    public static void putBoolean(Context context, String key, boolean value)
    {
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        spf.edit().putBoolean(key, value).apply(); }
    /** * 取数据(上下文，钥匙，未取得数据返回的默认值)(Int) */
    public static boolean getBoolean(Context context, String key, boolean value)
    {
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return spf.getBoolean(key, value); }
    /** * 删除单个偏好设置 */
    public static void deleteShare(Context context, String key)
    {
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        spf.edit().remove(key).apply(); }
    /** * 删除所有偏好设置 */
    public static void deleteShareAll(Context context, String key)
    {
        SharedPreferences spf = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        spf.edit().clear().apply(); }
}
