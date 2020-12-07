package com.team3.fdiosystem.repositories.api;

import com.team3.fdiosystem.models.DemoModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DemoApi {
    @GET("demo")
    Call<DemoModel> get();
}
