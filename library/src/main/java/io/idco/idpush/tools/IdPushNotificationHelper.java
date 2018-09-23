package io.idco.idpush.tools;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import io.idco.idpush.IDPush;
import io.idco.idpush.R;

/**
 * Created by 1HE on 09/23/2018
 */

@SuppressWarnings("ALL")
public class IdPushNotificationHelper {

    public static final String CHANNEL_DEFAULT = "default";

    public static final int ID_MESSAGE = 1;

    public static void showMessage(Context context, IDPush.Model model) {
        if (context == null)
            return;

        Intent intent = IDPush.getAvailableInstance().getNotificationHandler().getNotificationIntent(model);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = createBuilder(context, model.getNotificationTitle(), model.getNotificationBody(), model.getNotificationBody(),
                pendingIntent, 1, false);

        notify(context, ID_MESSAGE, builder);

        Intent broadcastIntent = new Intent(IDPush.BROADCAST_FIREBASE_MESSAGE);
        broadcastIntent.putExtra(IDPush.KEY_INTENT, model);
        LocalBroadcastManager.getInstance(context).sendBroadcast(broadcastIntent);
    }

    private static NotificationCompat.Builder createBuilder(Context context, String title, String contentText, String bigContent, PendingIntent pendingIntent, int numbers, boolean forceSilent) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_DEFAULT)
                .setSmallIcon(IDPush.getAvailableInstance().getResIcon())
                .setContentTitle(title)
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setColor(IDPush.getAvailableInstance().getColor());

        if (numbers > 1)
            mBuilder.setNumber(numbers);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(bigContent);
        mBuilder.setStyle(bigTextStyle);
        mBuilder.setDefaults(Notification.DEFAULT_ALL);

        return mBuilder;
    }

    private static void createChannel(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_DEFAULT, context.getString(R.string.app_name), importance);
            channel.setDescription(context.getString(R.string.app_name));
            channel.enableVibration(true);
            assert manager != null;
            manager.createNotificationChannel(channel);
        }
    }

    private static void notify(Context context, int id, NotificationCompat.Builder builder) {
        try {
            createChannel(context);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.notify(id, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancel(Context context, int id) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.cancel(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelAll(Context context) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.cancelAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
