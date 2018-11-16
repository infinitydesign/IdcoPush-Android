package io.idco.idpush.api;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.idco.idpush.tools.IdPushLogHelper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 1HE on 09/23/2018
 */

@SuppressWarnings("WeakerAccess")
public class IdPushRetrofitClient {

    private static final String TAG = "OKHttp_Retrofit_Client";

    public static final int TIMEOUT_CONNECT = 15000;
    public static final int TIMEOUT_READ = 30000;
    public static final int TIMEOUT_WRITE = 30000;

    public static final int CACHE_DEFAULT = 60;//second

    public static Retrofit getClient(final Context context, final ApiOptions options) {
        return create(context, GsonConverterFactory.create(), options);
    }

    private static Retrofit create(final Context context, GsonConverterFactory factory, ApiOptions options) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IdPushHolder.URL_API)
                .client(createOkHttpClient(context, options))
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static OkHttpClient createOkHttpClient(final Context context, final ApiOptions options) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        if (Build.VERSION.SDK_INT >= 17)
                            builder.addHeader("User-Agent", WebSettings.getDefaultUserAgent(context));
                        return chain.proceed(builder.build());
                    }
                });

        if (IdPushLogHelper.allowLog) {
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    @SuppressWarnings("unused")
    public static class ApiOptions {

        private boolean enableCache;
        private int cacheTime = CACHE_DEFAULT;

        public ApiOptions(boolean enableCache) {
            this.enableCache = enableCache;
        }

        public ApiOptions(boolean enableCache, int cacheTime) {
            this.enableCache = enableCache;
            this.cacheTime = cacheTime;
        }

        public boolean isEnableCache() {
            return enableCache;
        }

        public void setEnableCache(boolean enableCache) {
            this.enableCache = enableCache;
        }

        public int getCacheTime() {
            return cacheTime;
        }

        public void setCacheTime(int cacheTime) {
            this.cacheTime = cacheTime;
        }
    }

}
