package com.team3.fdiosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.MenuItem;
import com.team3.fdiosystem.viewmodels.MenuItemAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView menuView = (RecyclerView) findViewById(R.id.recyclerview);
//        RecyclerView.LayoutManager viewManager = new GridLayoutManager(getApplicationContext(),2);
//        menuView.setLayoutManager(viewManager);
        ArrayList<MenuItem> menuList = getMenu();
        MenuItemAdapter adapter = new MenuItemAdapter(this,menuList);
        GridLayoutManager horizontalLayout = new GridLayoutManager(MenuActivity.this,2,GridLayoutManager.HORIZONTAL,false);
        menuView.setLayoutManager(horizontalLayout);
        menuView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navbar, menu);
        return true;
    }

    private ArrayList<MenuItem> getMenu(){
        ArrayList<MenuItem> res = new ArrayList<>(10);
        for(int i=0;i<10;++i){
            res.add(new MenuItem(i, R.drawable.customsalad150_250));
        }
        return res;
    }
}