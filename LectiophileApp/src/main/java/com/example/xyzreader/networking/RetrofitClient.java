package com.example.xyzreader.networking;

import android.net.Uri;

import com.example.xyzreader.BuildConfig;
import com.example.xyzreader.api.LectiophileService;
import com.example.xyzreader.model.livedata.LiveDataCallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit mRetrofit;
    private static RetrofitClient mInstance;
    private static final String TAG = "RetrofitClient";

    private RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mRetrofit = new Retrofit.Builder().baseUrl(BuildConfig.URL)
                .client(new OkHttpClient().newBuilder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
    }

    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            return new RetrofitClient();
        }
        return mInstance;
    }

    public LectiophileService getLectiophileService() {
        return mRetrofit.create(LectiophileService.class);
    }
}
