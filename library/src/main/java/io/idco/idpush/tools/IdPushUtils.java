package io.idco.idpush.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Created by 1HE on 09/23/2018
 */

@SuppressWarnings({"unused", "DefaultFileTemplate"})
public class IdPushUtils {

    public static boolean isOnline(Context c) {
        try {
            ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert cm != null;
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

    public static int getAppVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getModel() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalizeModelPhone(model);
        } else {
            return capitalizeModelPhone(manufacturer) + " " + model;
        }
    }

    public static boolean isEmpty(String s) {
        if (s == null)
            return true;

        s = s.replaceAll("\\s+", "");

        return s.equals("") || s.length() == 0;
    }

    @SuppressWarnings("WeakerAccess")
    public static String capitalizeModelPhone(String s) {
        if (isEmpty(s))
            return "";
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}

