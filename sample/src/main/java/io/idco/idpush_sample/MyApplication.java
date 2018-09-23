package io.idco.idpush_sample;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;

import io.idco.idpush.IDPush;
import io.idco.idpush.tools.IdPushLogHelper;

/**
 * Created by 1HE on 9/22/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        IDPush.init(getApplicationContext(), "5ba77eddf6978e2b7c55dcf2")
                .setColor(Color.BLUE)
                .setResIcon(R.drawable.img_notification_small)
                .setNotificationHandler(new IDPush.NotificationHandler() {
                    @Override
                    public void onReceivedNotification(IDPush.Model model) {
                        IdPushLogHelper.d("sssssssssssss", "onReceivedNotification");
                    }

                    @Override
                    public Intent getNotificationIntent(IDPush.Model model) {
                        return new Intent();
                    }
                });
    }
}
