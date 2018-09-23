package io.idco.idpush;

import android.content.Context;
import android.os.Build;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import io.idco.idpush.api.IdPushApi;
import io.idco.idpush.api.IdPushApiUtils;
import io.idco.idpush.api.IdPushHolder;
import io.idco.idpush.api.IdPushResponse;
import io.idco.idpush.tools.IdPushLogHelper;
import io.idco.idpush.tools.IdPushNotificationHelper;
import io.idco.idpush.tools.IdPushSPHelper;
import io.idco.idpush.tools.IdPushUtils;
import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by 1HE on 09/23/2018
 */

public class IdPushMessagingService extends FirebaseMessagingService {

    public static final String TAG = IdPushMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        IdPushLogHelper.d(TAG, "onMessageReceived");
        if (remoteMessage.getNotification() != null) {
            IdPushLogHelper.d(TAG, "notification title : " + remoteMessage.getNotification().getTitle());
            IdPushLogHelper.d(TAG, "notification body : " + remoteMessage.getNotification().getBody());
        }
        IdPushLogHelper.d(TAG, "data : " + remoteMessage.getData());

        Map<String, String> data = remoteMessage.getData();
        if (data == null)
            return;

        String title = data.get("title");
        String content = data.get("alert");
        String dataJson = data.get("custom") == null ? "" : data.get("custom");

        IDPush.Model model = new IDPush.Model(title, content, dataJson);
        if (!IdPushUtils.isEmpty(content)) {
            IdPushNotificationHelper.showMessage(this, model);
        }

        IDPush.getAvailableInstance().getNotificationHandler().onReceivedNotification(model);
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        IdPushLogHelper.d(TAG, "onNewToken: " + s);
        if (IdPushUtils.isEmpty(s))
            return;

        IdPushSPHelper.setString(this, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_FIREBASE_TOKEN, s);

        if (!IdPushUtils.isOnline(this))
            return;

        callApiDeviceAdd(this, s);
    }

    public static void callApiDeviceAdd(final Context context, String token) {
        IdPushLogHelper.d(TAG, "callApiDeviceAdd : " + token);

        IdPushApi api = IdPushApiUtils.getIdPushApi(context, null);
        Observable<Response<IdPushResponse>> observable = api.deviceAdd(
                IdPushSPHelper.getString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_PROJECT_ID, ""),
                1,
                IdPushSPHelper.getString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_APP_VERSION, ""),
                IdPushUtils.getModel(),
                Build.VERSION.SDK_INT,
                token,
                1,
                2);
        IdPushApiUtils.run(context, IdPushHolder.DEVICE_ADD, observable, new IdPushApiUtils.IApiUtilsListener() {
            @Override
            public void onPreApi(int requestType) {

            }

            @Override
            public void onCompletedApi(int requestType) {
                IdPushSPHelper.setString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_FIREBASE_TOKEN_SYNC_API, getToken(context));
            }

            @Override
            public <T> void onSuccessApi(Response<T> rs, int requestType) {

            }

            @Override
            public <T> void onErrorApi(Response<T> rs, int requestType) {

            }
        });
    }

    public static String getToken(Context context) {
        return IdPushSPHelper.getString(context, IdPushSPHelper.SETTING, IdPushSPHelper.KEY_FIREBASE_TOKEN, "");
    }

}
