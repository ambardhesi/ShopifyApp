package com.nefariousdream.shopifyapp.data.source.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit sRetrofit = null;

    public ApiClient() {
    }

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            synchronized (Retrofit.class) {
                if (sRetrofit == null) {

                    OkHttpClient client = new OkHttpClient.Builder().build();
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl("https://shopicruit.myshopify.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                }
            }
        }
        return sRetrofit;
    }
}
