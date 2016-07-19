package com.example.azvk.rxjavaretrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Generator {

    private static final String API_URL = "http://54.186.57.223:8080/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit.Builder builder;

    static {
        builder = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}