package io.idco.idpush;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.google.firebase.FirebaseApp;

import java.io.Serializable;

import io.idco.idpush.tools.IdPushNotificationHelper;
import io.idco.idpush.tools.IdPushSPHelper;
import io.idco.idpush.tools.IdPushUtils;

/**
 * Created by 1HE on 9/22/2018.
 */

public class IDPush {

    @SuppressLint("StaticFieldLeak")
    private static IDPush availableInstance;

    private Context context;
    private int color = Color.BLACK;
    private int resIcon = R.drawable.img_notification_small;
    private NotificationHandler notificationHandler;

    public int getColor() {
        return color;
    }

    public IDPush setColor(int color) {
        this.color = color;
        return this;
    }

    public int getResIcon() {
        return resIcon;
    }

    public IDPush setResIcon(int resIcon) {
        this.resIcon = resIcon;
        return this;
    }

    public NotificationHandler getNotificationHandler() {
        return notificationHandler != null ? notificationHandler : new NotificationHandler() {
            @Override
            public void onReceivedNotification(Model model) {

            }

            @Override
            public Intent getNotificationIntent(Model model) {
                Intent intent = null;
                if (context != null)
                    intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                if (intent == null)
                    intent = new Intent();
                return intent;
            }
        };
    }

    @SuppressWarnings("UnusedReturnValue")
    public IDPush setNotificationHandler(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
        return this;
    }

    public static IDPush init(Context context, String projectId) {
        if (IdPushUtils.isEmpty(projectId)) {
            throw new IllegalArgumentException("projectId is not valid");
        }

        try {
            FirebaseApp.initializeApp(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        IdPushSPHelper.setString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_PROJECT_ID, projectId);
        IdPushSPHelper.setString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_PACKAGE_NAME, context.getPackageName());
        IdPushSPHelper.setString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_APP_VERSION, IdPushUtils.getAppVersionName(context));

        checkDeviceAdded(context);

        availableInstance = new IDPush();
        availableInstance.context = context;
        return availableInstance;
    }

    public static IDPush getAvailableInstance() {
        return availableInstance == null ? new IDPush() : availableInstance;
    }

    public static String getPlayerId(Context context) {
        return IdPushSPHelper.getString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_PLAYER_ID, "");
    }

    public static void checkDeviceAdded(Context context) {
        String lastToken = IdPushSPHelper.getString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_FIREBASE_TOKEN_SYNC_API, "");
        if (!lastToken.equals(IdPushMessagingService.getToken(context))) {
            IdPushMessagingService.callApiDeviceAdd(context, IdPushMessagingService.getToken(context));
        }
    }

    public static void cancelAllNotification(Context context) {
        IdPushNotificationHelper.cancelAll(context);
    }

    @SuppressWarnings("unused")
    public static class Model implements Serializable {

        private String notificationTitle;
        private String notificationBody;
        private String notificationJson;

        Model() {

        }

        Model(String notificationTitle, String notificationBody, String notificationJson) {
            this.notificationTitle = notificationTitle;
            this.notificationBody = notificationBody;
            this.notificationJson = notificationJson;
        }

        public String getNotificationTitle() {
            return notificationTitle;
        }

        public void setNotificationTitle(String notificationTitle) {
            this.notificationTitle = notificationTitle;
        }

        public String getNotificationBody() {
            return notificationBody;
        }

        public void setNotificationBody(String notificationBody) {
            this.notificationBody = notificationBody;
        }

        public String getNotificationJson() {
            return notificationJson;
        }

        public void setNotificationJson(String notificationJson) {
            this.notificationJson = notificationJson;
        }
    }

    public interface NotificationHandler {

        void onReceivedNotification(IDPush.Model model);

        Intent getNotificationIntent(IDPush.Model model);
    }
}
