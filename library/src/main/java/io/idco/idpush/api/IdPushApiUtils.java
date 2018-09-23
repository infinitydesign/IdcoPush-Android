package io.idco.idpush.api;

import android.annotation.SuppressLint;
import android.content.Context;

import io.idco.idpush.tools.IdPushLogHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by 1HE on 09/23/2018
 */

@SuppressWarnings("unused")
public class IdPushApiUtils {

    private static final String TAG = "OKHttp_ApiUtils";

    public static IdPushApi getIdPushApi(Context context, IdPushRetrofitClient.ApiOptions options) {
        return IdPushRetrofitClient.getClient(context, options).create(IdPushApi.class);
    }

    @SuppressLint("CheckResult")
    @SuppressWarnings("unused")
    public static <T> void run(final Object c, final int requestType, Observable<Response<T>> observable, final IApiUtilsListener listener) {
        boolean isActivity = c instanceof Context;
        if (!isActivity) {
            throw new IllegalArgumentException("c must be activity or context");
        }

        observable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        try {
            if (listener != null)
                listener.onPreApi(requestType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        observable.subscribe(new Consumer<Response<T>>() {
            @Override
            public void accept(Response<T> response) {
                IdPushLogHelper.d(TAG, "onNext" + response.code());
                try {

                    if (listener == null)
                        return;

                    listener.onCompletedApi(requestType);

                    if (isSuccessful(response)) {
                        listener.onSuccessApi(response, requestType);
                    } else {
                        IdPushLogHelper.d(TAG, "not success : " + response.code());
                        listener.onErrorApi(response, requestType);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                try {
                    IdPushLogHelper.d(TAG, "onError : " + throwable.getMessage());
                    if (listener != null) {
                        listener.onCompletedApi(requestType);
                        listener.onErrorApi(null, requestType);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Action() {
            @Override
            public void run() {
                IdPushLogHelper.d(TAG, "onCompleted");
            }
        });
    }


    @SuppressWarnings("WeakerAccess")
    public static <T> boolean isSuccessful(Response<T> response) {
        return response != null && response.isSuccessful() && response.body() != null;
    }

    @SuppressWarnings("unused")
    public static <T> boolean isCodeValidateError(Response<T> response) {
        return response != null && response.code() == IdPushHolder.CODE_DATA_NOT_VALID;
    }

    @SuppressWarnings("unused")
    public static <T> boolean isCodeNotFound(Response<T> response) {
        return response != null && response.code() == IdPushHolder.CODE_NOT_FOUND;
    }

    @SuppressWarnings("WeakerAccess")
    public static <T> boolean isCodeUnauthorized(Response<T> response) {
        return response != null && response.code() == IdPushHolder.CODE_UNAUTHORIZED;
    }

    @SuppressWarnings("WeakerAccess")
    public interface IApiUtilsListener {

        void onPreApi(int requestType);

        void onCompletedApi(int requestType);

        <T> void onSuccessApi(Response<T> rs, int requestType);

        <T> void onErrorApi(Response<T> rs, int requestType);

    }
}
