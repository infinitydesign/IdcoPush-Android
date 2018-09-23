package io.idco.idpush.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 1HE on 09/23/2018
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class IdPushSPHelper {

    public static final String SETTING = "IDPUSH_Setting_v1"; // not clear after update app

    public static final String KEY_FIREBASE_TOKEN_SYNC_API = "firebaseToken_syncAPi";
    public static final String KEY_FIREBASE_TOKEN = "firebaseToken";
    public static final String KEY_PROJECT_ID = "projectId";
    public static final String KEY_APP_VERSION = "appVersion";

    @SuppressWarnings("unused")
    public static boolean getBoolean(Context c, String settingName, String key, boolean def) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        return s.getBoolean(key, def);
    }

    @SuppressWarnings("unused")
    public static void setBoolean(Context c, String settingName, String key, boolean value) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    @SuppressWarnings("SameParameterValue")
    public static float getFloat(Context c, String settingName, String key, float def) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        return s.getFloat(key, def);
    }

    public static void setFloat(Context c, String settingName, String key, float value) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    @SuppressWarnings("unused")
    public static int getInt(Context c, String settingName, String key, int def) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        return s.getInt(key, def);
    }

    @SuppressWarnings("unused")
    public static void setInt(Context c, String settingName, String key, int value) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String getString(Context c, String settingName, String key, String def) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        return s.getString(key, def);
    }

    public static void setString(Context c, String settingName, String key, String value) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @SuppressWarnings({"unused", "SameParameterValue"})
    public static long getLong(Context c, String settingName, String key, long def) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        return s.getLong(key, def);
    }

    @SuppressWarnings("unused")
    public static void setLong(Context c, String settingName, String key, long value) {
        SharedPreferences s = c.getSharedPreferences(settingName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        editor.putLong(key, value);
        editor.apply();
    }
}