package com.danny.javier.api;

import com.danny.javier.api.ApiService;
import com.danny.javier.api.MainActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetofitInstance {

    public static RetofitInstance instance;
    ApiService apiInterface;

    RetofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiService.class); // Asignar el objeto ApiService correctamente

    }

    public static RetofitInstance getInstance(){
        if(instance == null){
            instance = new RetofitInstance();
        }
        return instance;
    }
}