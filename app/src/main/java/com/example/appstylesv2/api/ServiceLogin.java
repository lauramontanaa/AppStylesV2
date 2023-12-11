package com.example.appstylesv2.api;

import com.example.appstylesv2.model.Loger;
import com.example.appstylesv2.model.ResponseCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceLogin {
    @POST("login")
    public Call<ResponseCredentials> accessLogin(@Body Loger login);
}
