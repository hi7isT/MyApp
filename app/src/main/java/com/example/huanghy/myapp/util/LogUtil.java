package com.example.huanghy.myapp.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by huanghy on 2017/5/3.
 *
 *      日志打印工具类(在程序中输出log日志，并将日志保存到手机crashlog/LogUtil的目录下)
 *          主要是两个开关，isSave和具体的log开关，一个负责是否保存到sd卡，一个负责是否打印日志到control控制台.
 */

public class LogUtil {

    //是否保存到SD卡上
    public static boolean isSava = true;


    /**
     * debug开关.
     */
    public static boolean D = true;

    /**
     * info开关.
     */
    public static boolean I = true;

    /**
     * error开关.
     */
    public static boolean E = true;

    /**
     * 起始执行时间.
     */
    public static long startLogTimeInMillis = 0;
    private static DateFormat nowDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    static String nowDate = "";

    /**
     * debug日志的开关
     *
     * @param d
     */
    public static void debug(boolean d) {
        D = d;
    }

    /**
     * info日志的开关
     *
     * @param i
     */
    public static void info(boolean i) {
        I = i;
    }

    /**
     * error日志的开关
     *
     * @param e
     */
    public static void error(boolean e) {
        E = e;
    }

    /**
     * 设置日志的开关
     *
     * @param e
     */
    public static void setVerbose(boolean d, boolean i, boolean e) {
        D = d;
        I = i;
        E = e;
    }

    /**
     * 打开所有日志，默认全打开
     *
     */
    public static void openAll() {
        D = true;
        I = true;
        E = true;
    }

    /**
     * 关闭所有日志
     *
     */
    public static void closeAll() {
        D = false;
        I = false;
        E = false;
    }

    /**
     * debug日志
     *
     * @param tag
     * @param message
     */
    public static void d(String tag, String message) {
        if (D) Log.d(tag, message);
        save2File(tag, message);
    }

    /**
     * debug日志
     *
     * @param context
     * @param message
     */
    public static void d(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        d(tag, message);
    }

    /**
     * debug日志
     *
     * @param clazz
     * @param message
     */
    public static void d(Class<?> clazz, String message) {
        if (D) {
            String tag = clazz.getSimpleName();
            d(tag, message);
        }
    }

    /**
     * debug日志
     *
     * @param context
     * @param format
     * @param args
     */
    public static void d(Context context, String format, Object... args) {
        if (D) {
            String tag = context.getClass().getSimpleName();
            d(tag, buildMessage(format, args));
        }
    }

    /**
     * debug日志
     *
     * @param clazz
     * @param format
     * @param args
     */
    public static void d(Class<?> clazz, String format, Object... args) {
        if (D) {
            String tag = clazz.getSimpleName();
            d(tag, buildMessage(format, args));
        }
    }

    /**
     * info日志
     *
     * @param tag
     * @param message
     */
    public static void i(String tag, String message) {
        if (I) {
            Log.i(tag, message);
            save2File(tag, message);
        }
    }

    /**
     * info日志
     *
     * @param context
     * @param message
     */
    public static void i(Context context, String message) {
        if (I) {
            String tag = context.getClass().getSimpleName();
            i(tag, message);
        }
    }

    /**
     * info日志
     *
     * @param clazz
     * @param message
     */
    public static void i(Class<?> clazz, String message) {
        if (I) {
            String tag = clazz.getSimpleName();
            i(tag, message);
        }
    }

    /**
     * info日志
     *
     * @param context
     * @param format
     * @param args
     */
    public static void i(Context context, String format, Object... args) {
        if (I) {
            String tag = context.getClass().getSimpleName();
            i(tag, buildMessage(format, args));
        }
    }

    /**
     * info日志
     *
     * @param clazz
     * @param format
     * @param args
     */
    public static void i(Class<?> clazz, String format, Object... args) {
        if (I) {
            String tag = clazz.getSimpleName();
            i(tag, buildMessage(format, args));
        }
    }


    /**
     * error日志
     *
     * @param tag
     * @param message
     */
    public static void e(String tag, String message) {
        if (E) {
            Log.e(tag, message);
            save2File(tag, message);
        }
    }

    /**
     * error日志
     *
     * @param context
     * @param message
     */
    public static void e(Context context, String message) {
        if (E) {
            String tag = context.getClass().getSimpleName();
            e(tag, message);
        }
    }

    /**
     * error日志
     *
     * @param clazz
     * @param message
     */
    public static void e(Class<?> clazz, String message) {
        if (E) {
            String tag = clazz.getSimpleName();
            e(tag, message);
        }
    }


    /**
     * error日志
     *
     * @param context
     * @param format
     * @param args
     */
    public static void e(Context context, String format, Object... args) {
        if (E) {
            String tag = context.getClass().getSimpleName();
            e(tag, buildMessage(format, args));
        }
    }

    /**
     * error日志
     *
     * @param clazz
     * @param format
     * @param args
     */
    public static void e(Class<?> clazz, String format, Object... args) {
        if (E) {
            String tag = clazz.getSimpleName();
            e(tag, buildMessage(format, args));
        }
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(String tag) {
        Calendar current = Calendar.getInstance();
        startLogTimeInMillis = current.getTimeInMillis();
        Log.d(tag, "日志计时开始：" + startLogTimeInMillis);
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(Context context) {
        String tag = context.getClass().getSimpleName();
        prepareLog(tag);
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(Class<?> clazz) {
        String tag = clazz.getSimpleName();
        prepareLog(tag);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param tag       标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(String tag, String message, boolean printTime) {
        if (D) {
            Calendar current = Calendar.getInstance();
            long endLogTimeInMillis = current.getTimeInMillis();
            Log.d(tag, message + ":" + (endLogTimeInMillis - startLogTimeInMillis) + "ms");
        }
    }


    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Context context, String message, boolean printTime) {
        if (D) {
            String tag = context.getClass().getSimpleName();
            d(tag, message, printTime);
        }
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param clazz     标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Class<?> clazz, String message, boolean printTime) {
        if (D) {
            String tag = clazz.getSimpleName();
            d(tag, message, printTime);
        }
    }

    /**
     * format日志
     *
     * @param format
     * @param args
     * @return
     */
    private static String buildMessage(String format, Object... args) {
        String msg = (args == null) ? format : String.format(Locale.US, format, args);
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);
                caller = callingClass + "." + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s",
                Thread.currentThread().getId(), caller, msg);
    }


    //用于格式化日期,作为日志文件名的一部分
    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String save2File(String tag, String msg) {
        if (!isSava)
            return "";

        if ("".equals(nowDate))
            nowDate = nowDateFormatter.format(new Date());
        String time = formatter.format(new Date());
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("time:" + time);
        sb.append("\n");
        sb.append("tag:" + tag);
        sb.append("\n");
        sb.append("msg:" + msg);
        sb.append("\n");
        try {
            String fileName = nowDate + ".log";
            String file_dir = getFilePath();
            File dir = new File(file_dir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(file_dir + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(sb.toString().getBytes());
            fos.close();
            return fileName;
        } catch (Exception e) {
        }
        return null;
    }

    private static String getFilePath() {
        String file_dir = "";
        // SD卡是否存在
        boolean isSDCardExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        // Environment.getExternalStorageDirectory()相当于File file=new File("/sdcard")
        boolean isRootDirExist = Environment.getExternalStorageDirectory().exists();
        if (isSDCardExist && isRootDirExist) {
            file_dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/crashlog/LogUtil";
        }
        return file_dir;
    }
}
