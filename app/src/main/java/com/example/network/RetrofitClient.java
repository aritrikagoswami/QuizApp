package com.example.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static RetrofitClient instance = null;
    public API quizApi;
    public RetrofitClient() {
        Retrofit obj_retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        quizApi = obj_retrofit.create(API.class);
    }
    public static synchronized RetrofitClient getInstance() {
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }
    public API getQuizApi() {
        return  quizApi;
    }



}
