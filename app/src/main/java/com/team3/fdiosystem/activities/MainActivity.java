package com.team3.fdiosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.Item;
import com.team3.fdiosystem.models.ItemGroup;
import com.team3.fdiosystem.viewmodels.MenuAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView menuView = (RecyclerView) findViewById(R.id.menu_recycler);
        ArrayList<Object> menu = getMenu();

        LinearLayoutManager menuLayout = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        menuView.setLayoutManager(menuLayout);
        MenuAdapter adapter = new MenuAdapter(this,menu);
        menuView.setAdapter(adapter);
        findViewById(R.id.add_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.add(new Item(R.drawable.cfsua_bg));
                adapter.notifyDataSetChanged();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent i = new Intent(this,CartActivity.class);
                MainActivity.this.startActivity(i);
                return true;
            case R.id.login:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Object> getMenu(){
        ArrayList<Object> res = new ArrayList<>(10);
        res.add(new ItemGroup(R.drawable.login_bg,R.drawable.foodex,R.drawable.foodex));
        System.out.println(res.get(0));
        for(int i=1;i<10;++i){
            res.add(new Item(R.drawable.foodex));
        }
        return res;
    }
}