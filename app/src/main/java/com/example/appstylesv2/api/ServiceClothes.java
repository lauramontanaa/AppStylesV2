package com.example.appstylesv2.api;

import com.example.appstylesv2.model.Clothes;
import com.example.appstylesv2.model.ResponseClothes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceClothes {
    @GET("clothes")
    public Call <ResponseClothes> accessClothes();
}
