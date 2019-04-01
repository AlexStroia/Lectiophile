package co.alexdev.data.networking;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

import co.alexdev.data.BuildConfig;
import co.alexdev.data.api.LectiophileService;
import co.alexdev.data.model.livedata.LiveDataCallAdapterFactory;
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
