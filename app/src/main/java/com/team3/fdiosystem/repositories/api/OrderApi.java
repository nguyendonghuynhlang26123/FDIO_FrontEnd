package com.team3.fdiosystem.repositories.api;

import com.team3.fdiosystem.models.OrderModel;
import com.team3.fdiosystem.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.*;

public interface OrderApi {
    @GET("/order-queue")
    Call<OrderModel[]> get();

    @POST("/order-queue/")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseModel> post(@Body OrderModel model);
}
