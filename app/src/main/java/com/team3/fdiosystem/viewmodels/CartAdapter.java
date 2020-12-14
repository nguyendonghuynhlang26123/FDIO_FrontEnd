package com.team3.fdiosystem.viewmodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.CartItem;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final Context context;
    private final ArrayList<CartItem> data;

    public CartAdapter(Context context, ArrayList<CartItem> data){
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_view,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem currentItem = data.get(position);
        holder.img.setImageResource(currentItem.img);
        holder.name.setText(currentItem.name);
        holder.price.setText(String.valueOf(currentItem.price)+"$");
        holder.quantity.setText(String.valueOf(currentItem.quantity));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected static class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView price;
        TextView quantity;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.cart_item_img);
            name = (TextView)itemView.findViewById(R.id.cart_item_name);
            price = (TextView)itemView.findViewById(R.id.cart_item_price);
            quantity = (TextView)itemView.findViewById(R.id.cart_item_quantity);
        }
    }
}
