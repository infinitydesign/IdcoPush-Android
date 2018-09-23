package io.idco.idpush.tools;

import android.util.Log;

import io.idco.idpush.BuildConfig;

/**
 * Created by 1HE on 09/23/2018
 */

@SuppressWarnings({"unused", "DefaultFileTemplate"})
public class IdPushLogHelper {

    public static final boolean allowLog = BuildConfig.DEBUG;

    @SuppressWarnings("UnusedReturnValue")
    public static int d(String tag, String message) {
        if (allowLog)
            return Log.d(tag, message);
        return 0;
    }

    public static int d(String tag, String message, Throwable tr) {
        if (allowLog)
            return Log.d(tag, message, tr);
        return 0;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int d(String tag, int message) {
        if (allowLog)
            return Log.d(tag, message + "");
        return 0;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int d(String tag, boolean message) {
        if (allowLog)
            return Log.d(tag, message + "");
        return 0;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int d(String tag, long message) {
        if (allowLog)
            return Log.d(tag, message + "");
        return 0;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int d(String tag, float message) {
        if (allowLog)
            return Log.d(tag, message + "");
        return 0;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int d(String tag, double message) {
        if (allowLog)
            return Log.d(tag, message + "");
        return 0;
    }

    public static int d(String tag, int message, Throwable tr) {
        if (allowLog)
            return Log.d(tag, message + "", tr);
        return 0;
    }

    @SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
    public static int e(String tag, String message, Exception throwable) {
        if (allowLog)
            return Log.e(tag, message, throwable);
        return 0;
    }

    @SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
    public static int e(String tag, String message, Throwable throwable) {
        if (allowLog)
            return Log.e(tag, message, throwable);
        return 0;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int e(String tag, String message) {
        if (allowLog)
            return Log.e(tag, message);
        return 0;
    }

    public static int i(String tag, String message, Exception throwable) {
        if (allowLog)
            return Log.i(tag, message, throwable);
        return 0;
    }

    public static int i(String tag, String message, Throwable throwable) {
        if (allowLog)
            return Log.i(tag, message, throwable);
        return 0;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int i(String tag, String message) {
        if (allowLog)
            return Log.i(tag, message);
        return 0;
    }

    public static int w(String tag, String message) {
        if (allowLog)
            return Log.w(tag, message);
        return 0;
    }

    public static int w(String tag, String message, Throwable tr) {
        if (allowLog)
            return Log.w(tag, message, tr);
        return 0;
    }

    public static int v(String tag, String message) {
        if (allowLog)
            return Log.v(tag, message);
        return 0;
    }

    public static int v(String tag, String message, Throwable tr) {
        if (allowLog)
            return Log.v(tag, message, tr);
        return 0;
    }
}
