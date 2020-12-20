package com.team3.fdiosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.viewmodels.CartAdapter;
import com.team3.fdiosystem.viewmodels.CheckoutDialog;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ArrayList<CartItem> cart = getCart();
        CartAdapter adapter = new CartAdapter(this, cart);
        LinearLayoutManager cartLayout = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView view = findViewById(R.id.cart_recycler_view);
        view.setAdapter(adapter);
        view.setLayoutManager(cartLayout);
        Button ckout = findViewById(R.id.ckoutBtn);
        ckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new CheckoutDialog();
                dialog.show(getSupportFragmentManager(),"Checkout");
            }
        });
    }
    private ArrayList<CartItem> getCart(){
        ArrayList<CartItem> cart = new ArrayList<>(10);
        for(int i=0; i<10;++i){
            cart.add(new CartItem(i,R.drawable.cfsua_bg,i));
        }
        return cart;
    }
}