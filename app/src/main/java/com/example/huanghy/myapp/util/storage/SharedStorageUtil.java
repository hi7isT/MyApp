package com.example.huanghy.myapp.util.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by huanghy on 2017/4/9.
 *
 * SharedPrefererence存储(以键值对的形式进行存储)以及读取    (storage  [ˈstɔ:rɪdʒ]-->>"存储"的意思)
 *      存储位置：可以在DDMS下的shared_prefs目录下查看，是app_share.xml文件.
 *
 *      补充：在应用中任何位置都可以使用该SharedUtil类来实现SharedPreference存储
 *
 *      Android 系统中主要提供了三种方式用于简单地实现数据持久化功能，
 *      即文件存储、SharedPreference 存储以及数据库存储。当然，除了这三种方式之外，你还可
 *      以将数据保存在手机的SD 卡中，不过使用文件、SharedPreference 或数据库来保存数据会相
 *      对更简单一些，而且比起将数据保存在SD 卡中会更加的安全。
 *
 */

public class SharedStorageUtil {

    private static final String SHARED_FILE_NAME="app_share";//文件名(可根据实际情况自己修改)


    /**
     * 获取SharedPreference实例(该类的关键所在)
     *
     *      android原生获取该实例有三种方式:
     *          Context 类中的getSharedPreferences()方法.
     *          Activity 类中的getPreferences()方法
     *          PreferenceManager 类中的getDefaultSharedPreferences()方法
     * @param context
     * @return
     */
    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        //Context.MODE_PRIVATE  表示：只有当前应用程序才可以对SharedPreferences(app_share.xml)文件进行读写
        return context.getSharedPreferences(SHARED_FILE_NAME, Context.MODE_PRIVATE);
    }



    /************************************************保存数据*************************************/

    /**
     * 保存字符串
     * @param context
     * @param key   键值
     * @param value 真实值
     */
    public static void putString(Context context,String key,String value) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 保存int型数据
     * @param context
     * @param key   键值
     * @param value 真实值
     */
    public static void putInt(Context context,String key, int value) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    /**
     * 保存boolean型数据
     * @param context
     * @param key   键值
     * @param value 真实值
     */
    public static void putBoolean(Context context,String key, boolean value) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 保存long型数据
     * @param context
     * @param key   键值
     * @param value 真实值
     */
    public static void putLong(Context context,String key, long value) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor edit = sharedPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }


    /************************************************获取数据*************************************/

    /**
     * 获取保存的字符串，指定默认值为null，即不需要考虑默认值
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context,String key){
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }

    /**
     * 获取保存的字符串，外部可指定默认值
     * @param context
     * @param key
     * @param defValue  默认值
     * @return
     */
    public static String getString(Context context,String key,String defValue) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return  sharedPreferences.getString(key, defValue);
    }

    /**
     * 获取int型数据，指定默认之为0，不需要外部考虑默认值
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }

    /**
     * 获取int型数据，外部可指定默认值
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key,int defValue) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * 获取boolean型数据，指定默认值为false，即不需要考虑默认值
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context,String key) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key,false);
    }

    /**
     * 获取boolean型数据，外部可指定默认值
     * @param context
     * @param key       键值
     * @param defValue  默认值
     * @return
     */
    public static boolean getBoolean(Context context,String key,boolean defValue) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key,defValue);
    }

    /**
     * 获取long型数据，指定默认之为0，不需要外部考虑默认值
     * @param context
     * @param key
     * @return
     */
    public static long getLong(Context context,String key) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getLong(key, 0);
    }

    /**
     * 获取long型数据，外部可指定默认值
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context,String key,long defValue) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getLong(key, defValue);
    }



    /************************************************删除数据*************************************/

    /**
     * 删除指定key所对应的数据
     * @param context
     * @param key   键值
     */
    public static void remove(Context context,String key) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.commit();
    }

}
