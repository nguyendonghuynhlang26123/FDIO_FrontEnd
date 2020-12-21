package com.team3.fdiosystem.repositories.services;

import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.models.UserModel;
import com.team3.fdiosystem.repositories.api.UserApi;

import retrofit2.Call;

public class UserService {
    private UserApi api = null;
    public UserService(){
        api = RetrofitSingleton.getInstance().create(UserApi.class);
    }

    //Login
    public Call<ResponseModel> login(UserModel order) { return api.post(order);}
}
