package com.team3.fdiosystem.repositories.services;

import com.team3.fdiosystem.models.DemoModel;
import com.team3.fdiosystem.repositories.api.DemoApi;

import retrofit2.Call;

public class DemoService {
    private DemoApi api = null;
    public DemoService(){
        api = RetrofitSingleton.getInstance().create(DemoApi.class);
    }

    public Call<DemoModel> getDemo(){
        return api.get();
    }
}
