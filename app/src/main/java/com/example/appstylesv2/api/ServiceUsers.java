package com.example.appstylesv2.api;

import com.example.appstylesv2.model.Loger;
import com.example.appstylesv2.model.ResponseCredentials;
import com.example.appstylesv2.model.ResponseUser;
import com.example.appstylesv2.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ServiceUsers {
    @GET("users")
    public Call<ResponseUser> infoUser();

    @POST("users")
    public static Call<ResponseUser> createUser(@Body User usuario) {
        return null;
    }
    @PUT("users")
    public Call<ResponseUser> updateUser(@Body User update);
}
