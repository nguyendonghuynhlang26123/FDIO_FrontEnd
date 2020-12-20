package com.team3.fdiosystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.Item;
import com.team3.fdiosystem.viewmodels.MenuAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RecyclerView menuView = (RecyclerView) findViewById(R.id.menu_recycler);
        AsymmetricGridView menuView = findViewById(R.id.Gridview);
        menuView.setRequestedColumnWidth(Utils.dpToPx(this,100));
        ArrayList<Item> menu = getMenu();

//        LinearLayoutManager menuLayout = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
//        menuView.setLayoutManager(menuLayout);
        MenuAdapter adapter = new MenuAdapter(this,menu);
        AsymmetricGridViewAdapter menuAdapter = new AsymmetricGridViewAdapter(this, menuView, adapter);
        menuView.setAdapter(menuAdapter);
        menuView.setAllowReordering(true);
        menuView.isAllowReordering();
        findViewById(R.id.add_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = menu.size();
                menu.add(new Item(4,4, pos,R.drawable.cfsua_bg));
                adapter.notifyDataSetChanged();
            }
        });
        menuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(view.isLongClickable()==false){
                    view.clearAnimation();
                    view.setLongClickable(true);
                    view.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
                }
                else{
                    Intent i = new Intent(MainActivity.this,MenuActivity.class);
                    i.putExtra("type", position);
                    startActivity(i);
                }
            }
        });
        menuView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Animation shake = AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake);
                view.startAnimation(shake);
                view.setLongClickable(false);
                view.findViewById(R.id.fab).setVisibility(View.VISIBLE);
                return true;
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

    private ArrayList<Item> getMenu(){
        ArrayList<Item> res = new ArrayList<>(10);
//        res.add(new ItemGroup(R.drawable.login_bg,R.drawable.foodex,R.drawable.foodex));
//        System.out.println(res.get(0));
        res.add(new Item(2,4,0,R.drawable.login_bg));
        res.add(new Item(2,2,1,R.drawable.customsalad150_250));
        res.add(new Item(2,2,2,R.drawable.customsalad150_250));
        res.add(new Item(3,4,3,R.drawable.login_bg));
        for(int i=4;i<10;++i){
            res.add(new Item(2+(i%3),3, i,R.drawable.foodex));
        }
        return res;
    }
}