package com.app.engineeringaitest.network;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitApiInterface {
    private static Retrofit retrofit = null;
    private static int CONNECTION_TIME_OUT = 5;
    private static String BASE_URL = "http://sd2-hiring.herokuapp.com/";

    public static RestApiMethods getRestApiMethods() {
        return createRetrofit().create(RestApiMethods.class);
    }

    private static Retrofit createRetrofit() {
        OkHttpClient.Builder httpClient = getBuilder();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit;
    }

    @NonNull
    private static OkHttpClient.Builder getBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(CONNECTION_TIME_OUT * 3L, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIME_OUT * 3L, TimeUnit.SECONDS);

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        return httpClient;
    }
}