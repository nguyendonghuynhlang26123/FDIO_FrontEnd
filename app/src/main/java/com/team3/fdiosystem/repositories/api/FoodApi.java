package com.team3.fdiosystem.repositories.api;

import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.OrderModel;
import com.team3.fdiosystem.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodApi {
    @GET("/food")
    Call<FoodModel[]> get();

    @GET("/food/{id}")
    Call<FoodModel> get(@Path("id")String id);

    @POST("/food")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseModel> post(@Body FoodModel model);

    @PUT("/food/{id}")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseModel> put(@Path("id")String id, @Body FoodModel model);

    @DELETE("/food/{id}")
    Call<ResponseModel> delete(@Path("id")String id);

    @DELETE("/food")
    Call<ResponseModel> delete(@Query("ids")String[] ids);
}
