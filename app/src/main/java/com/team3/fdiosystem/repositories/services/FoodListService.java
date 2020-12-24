package com.team3.fdiosystem.repositories.services;

import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.repositories.api.FoodListApi;

import retrofit2.Call;

public class FoodListService {
    private FoodListApi api = null;
    public FoodListService(){
        api = RetrofitSingleton.getInstance().create(FoodListApi.class);
    }

    //Get list of Food Lists
    public Call<FoodListModel[]> getFoodLists(){return api.get();}

    //Get Food List by id
    public Call<FoodListModel> getFoodListById(String id){return api.get(id);}

    //Create Food List
    public Call<ResponseModel> createFoodList(FoodListModel foodList) { return api.post(foodList);}

    //Update Food
    public Call<ResponseModel> updateFoodList(String id, FoodListModel foodList) { return api.put(id ,foodList);}

    //Delete Food
    public Call<ResponseModel> deleteFoodListById(String id){return api.delete(id);}

    //Delete list of foods
    public Call<ResponseModel> deleteFoodListByIdList(String[] ids){return api.delete(ids);}
}
