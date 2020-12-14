package com.team3.fdiosystem.viewmodels;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.activities.MenuActivity;
import com.team3.fdiosystem.models.MenuItem;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ItemView> {
    ArrayList<MenuItem> list;
    final Context context;
    protected class ItemView extends RecyclerView.ViewHolder {
        TextView description;
        TextView price;
        TextView name;
        ImageView img;
        public CardView cardView;
        public ItemView(@NonNull View itemView) {
            super(itemView);
            description = (TextView)itemView.findViewById(R.id.ingredient);
            price = (TextView)itemView.findViewById(R.id.price);
            name = (TextView)itemView.findViewById(R.id.salad_name);
            img = (ImageView)itemView.findViewById(R.id.salad_img);
            cardView =(CardView)itemView.findViewById(R.id.card_holder);
        }
        public void setDescription(String text){
            description.setText(text);
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


    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        MenuItem currentItem = list.get(position);
        holder.setDescription(currentItem.description);
        holder.setName(currentItem.name);
        holder.setPrice(String.valueOf(currentItem.price));
        holder.setImg(currentItem.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MenuActivity.class);
                i.putExtra("type", position);
                context.startActivity(i);
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

