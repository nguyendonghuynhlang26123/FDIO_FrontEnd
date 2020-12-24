package com.team3.fdiosystem.repositories.api;

import com.team3.fdiosystem.models.FoodListModel;
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

public interface FoodListApi {
    @GET("/food-list")
    Call<FoodListModel[]> get();

    @GET("/food-list/{id}")
    Call<FoodListModel> get(@Path("id")String id);

    @POST("/food-list")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseModel> post(@Body FoodListModel model);

    @PUT("/food-list/{id}")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseModel> put(@Path("id")String id, @Body FoodListModel model);

    @DELETE("/food-list/{id}")
    Call<ResponseModel> delete(@Path("id")String id);

    @DELETE("/food-list")
    Call<ResponseModel> delete(@Query("ids")String[] ids);
}
