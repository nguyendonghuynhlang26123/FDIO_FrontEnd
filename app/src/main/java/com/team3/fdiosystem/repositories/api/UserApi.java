package com.team3.fdiosystem.repositories.api;

import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseModel> post(@Body UserModel model);
}
