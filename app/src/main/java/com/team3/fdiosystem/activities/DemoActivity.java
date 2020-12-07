package com.team3.fdiosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.ActivityDemoBinding;
import com.team3.fdiosystem.viewmodels.DemoVM;

public class DemoActivity extends AppCompatActivity {
    static Context context;
    ActivityDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        context = getApplicationContext();

        setupBinding();
    }

    private void setupBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
        DemoVM demo = new DemoVM();
        binding.setDemo(demo);
    }

    public static Context getContext() {
        return context;
    }
}