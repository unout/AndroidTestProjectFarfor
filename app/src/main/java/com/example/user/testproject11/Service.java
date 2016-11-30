package com.example.user.testproject11;

import com.example.user.testproject11.model.Catalog;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/getyml/?key=ukAXxeJYZN")
    Call<Catalog> getCatalog();
}
