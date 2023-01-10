package com.dk.food;

import android.content.Context;
import android.widget.Toast;

import com.dk.food.models.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReqestManager {
    private static ReqestManager reqestManager;
    private static Retrofit retrofit;

    private ReqestManager(){
        retrofit=new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized ReqestManager getInstance(){
        if(reqestManager==null){
            reqestManager=new ReqestManager();
        }
        return reqestManager;
    }
    public Apis getApi(){
        return retrofit.create(Apis.class);
    }
}
