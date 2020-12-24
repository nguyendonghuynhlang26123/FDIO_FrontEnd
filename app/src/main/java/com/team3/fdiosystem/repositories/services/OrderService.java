package com.team3.fdiosystem.repositories.services;

import com.team3.fdiosystem.models.OrderModel;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.repositories.api.OrderApi;

import retrofit2.Call;
import retrofit2.Response;

public class OrderService {
    private OrderApi api = null;
    public OrderService(){
        api = RetrofitSingleton.getInstance().create(OrderApi.class);
    }

    //Get Orders
    public Call<OrderModel[]> getOrders(){return api.get();}

    //Create Order
    public Call<ResponseModel> createOrder(OrderModel order) { return api.post(order);}
}
