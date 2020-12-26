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
import android.widget.TextView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.MenuItem;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.MenuItemAdapter;
import com.team3.fdiosystem.viewmodels.OrderedItemAdapter;

import java.util.ArrayList;

public class OrderedCheck extends AppCompatActivity {
    ArrayList<Store.OrderedItem> list;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_check);
        RecyclerView menuView = findViewById(R.id.recyclerview);
//        Intent i = getIntent();
//        int type = i.getIntExtra("type",0);
        list = Store.get_instance().getOrderedList();
        OrderedItemAdapter adapter = new OrderedItemAdapter(this,list);
        GridLayoutManager vLayout = new GridLayoutManager(OrderedCheck.this,2,GridLayoutManager.VERTICAL,false);
        menuView.setLayoutManager(vLayout);
        menuView.setAdapter(adapter);
        TextView order_id = findViewById(R.id.ordered_id);
        TextView order_time = findViewById(R.id.ordered_time);
//        order_id.setText("000000001");
//        order_time.setText("12:00:00 Mon 12/Dec/2020");
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
                OrderedCheck.this.startActivity(i);
                return true;
            case R.id.login:
                Intent l = new Intent(this,ManagementHomepage.class);
                OrderedCheck.this.startActivity(l);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}