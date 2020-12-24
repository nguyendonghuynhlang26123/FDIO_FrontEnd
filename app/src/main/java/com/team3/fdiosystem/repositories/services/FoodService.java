package com.team3.fdiosystem.repositories.services;

import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.OrderModel;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.repositories.api.FoodApi;

import retrofit2.Call;

public class FoodService {
    private FoodApi api = null;
    public FoodService(){
        api = RetrofitSingleton.getInstance().create(FoodApi.class);
    }

    //Get list of Foods
    public Call<FoodModel[]> getFoods(){return api.get();}

    //Get Food by id
    public Call<FoodModel> getFoodById(String id){return api.get(id);}

    //Create Food
    public Call<ResponseModel> createFood(FoodModel order) { return api.post(order);}

    //Update Food
    public Call<ResponseModel> updateFood(String id, FoodModel order) { return api.put(id ,order);}

    //Delete Food
    public Call<ResponseModel> deleteFoodById(String id){return api.delete(id);}

    //Delete list of foods
    public Call<ResponseModel> deleteFoodByIdList(String[] ids){return api.delete(ids);}
}
