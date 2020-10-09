package com.example.jakfood.network;

import com.example.jakfood.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    private static Retrofit setInit(){
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build();

    }

    public  static APIService getInstance(){
        return setInit().create(APIService.class);

    }

}
