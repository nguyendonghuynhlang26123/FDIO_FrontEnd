package com.team3.fdiosystem.viewmodels;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.activities.MenuActivity;
import com.team3.fdiosystem.models.MenuItem;
import com.team3.fdiosystem.models.Store;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ItemView> {
    ArrayList<MenuItem> list;
    final Context context;
    protected class ItemView extends RecyclerView.ViewHolder {
        Button addBtn;
        TextView price;
        TextView name;
        ImageView img;
        public CardView cardView;
        public ItemView(@NonNull View itemView) {
            super(itemView);
            addBtn = itemView.findViewById(R.id.addBtn);
            price = (TextView)itemView.findViewById(R.id.menu_item_price);
            name = (TextView)itemView.findViewById(R.id.menu_item_title);
            img = (ImageView)itemView.findViewById(R.id.salad_img);
            cardView =(CardView)itemView.findViewById(R.id.card_holder);
        }

        public void setPrice(String price){
            this.price.setText("Price: "+price+"$");
        }
        public void setName(String name){
            this.name.setText(name);
        }
        public void setImg(int image_res){
            img.setImageResource(image_res);
        }
    }
    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item,parent,false);
        return new ItemView(itemView);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        MenuItem currentItem = list.get(position);
        holder.setName(currentItem.name);
        holder.setPrice(String.valueOf(currentItem.price));
        holder.setImg(currentItem.img);
        holder.addBtn.setOnClickListener(v -> Store.get_instance().addToCart(currentItem,1));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(context,CheckoutDialog.class);
//                i.putExtra("type", position);
//                context.startActivity(i);
            }
        });
    }
    public MenuItemAdapter(Context context, ArrayList<MenuItem> list){
        this.list=list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

