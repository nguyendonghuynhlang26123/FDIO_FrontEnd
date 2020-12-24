package com.team3.fdiosystem.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.MenuItem;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.MenuItemAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    ArrayList<MenuItem> menuList;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView menuView = (RecyclerView) findViewById(R.id.recyclerview);
//        RecyclerView.LayoutManager viewManager = new GridLayoutManager(getApplicationContext(),2);
//        menuView.setLayoutManager(viewManager);
        Intent i = getIntent();
        int type = i.getIntExtra("type",0);
        menuList = Store.get_instance().getMenuItems(type);
        MenuItemAdapter adapter = new MenuItemAdapter(this,menuList);
        GridLayoutManager horizontalLayout = new GridLayoutManager(MenuActivity.this,2,GridLayoutManager.HORIZONTAL,false);
        menuView.setLayoutManager(horizontalLayout);
        menuView.setAdapter(adapter);
        FloatingActionButton addItemBtn = findViewById(R.id.add_menu_item_fab);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, MenuItemModifyForm.class);
                i.putExtra("type",type);
                startActivity(i);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent i = new Intent(this,CartActivity.class);
                MenuActivity.this.startActivity(i);
                return true;
            case R.id.login:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}