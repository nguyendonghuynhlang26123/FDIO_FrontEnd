package com.team3.fdiosystem.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.models.DemoModel;
import com.team3.fdiosystem.repositories.services.DemoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoVM extends BaseObservable {
    private String text;
    private DemoService service;

    public DemoVM(){
        service = new DemoService();
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    public void onClick() {
        service.getDemo().enqueue(new Callback<DemoModel>() {
            @Override
            public void onResponse(Call<DemoModel> call, Response<DemoModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                DemoModel chat = response.body();
                setText(chat.getText());
            }

            @Override
            public void onFailure(Call<DemoModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }
}
