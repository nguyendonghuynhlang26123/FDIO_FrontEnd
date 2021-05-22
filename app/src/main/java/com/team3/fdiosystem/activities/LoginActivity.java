package com.team3.fdiosystem.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.databinding.ActivityLoginBinding;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.models.UserModel;
import com.team3.fdiosystem.repositories.services.UserService;
import com.team3.fdiosystem.viewmodels.LoginVM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    LoginVM vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        vm = new LoginVM();
        binding.setVm(vm);
        binding.loginBtn.setOnClickListener(l->{
            vm.setNotify("");
            Login(vm.getUsername(),vm.getPassword());
        });
    }

    public void Login(String username, String password){
        Log.i("LONG_LOGIN",username + password);
        if (username.trim().length() == 0 || password.trim().length() == 0){
            vm.setNotify("Don't leave any blank!");
            return;
        }

        UserModel user = new UserModel(username,password);
        UserService service = new UserService();
        vm.setLoginSpinner(true);
        service.login(user).enqueue(new Callback<ResponseModel>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("successful")){
                    vm.setLoginSpinner(false);
                    Store.get_instance().setMode(Utils.ADMIN_MODE);
                    Intent i = new Intent(LoginActivity.this, AdminMenuActivity.class);
                    LoginActivity.this.startActivity(i);
                }
                else {
                    vm.setLoginSpinner(false);
                    vm.setNotify(response.body().getErr());
                    Snackbar.make(findViewById(R.id.login_main), "Login failed"
                            ,Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
}
