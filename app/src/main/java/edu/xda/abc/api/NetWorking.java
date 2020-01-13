package edu.xda.abc.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import edu.xda.abc.utils.Utils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorking {
//    private static final String BASE_URL = "http://192.168.1.16:3000/";
//    private static final String BASE_URL = "http://192.168.55.28:3000/";
//    private static final String BASE_URL = "http://192.168.1.37:3000/";

    public static Retrofit getMovieClient() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(Utils.BASE_URL)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(provideLoggingInterceptor())
                .retryOnConnectionFailure(true)
                .build();
    }
}
