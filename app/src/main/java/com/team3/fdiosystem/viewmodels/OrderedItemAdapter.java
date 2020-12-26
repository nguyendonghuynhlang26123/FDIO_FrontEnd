package com.team3.fdiosystem.viewmodels;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.MenuItem;
import com.team3.fdiosystem.models.Store;

import java.util.ArrayList;

public class OrderedItemAdapter extends RecyclerView.Adapter<OrderedItemAdapter.ItemView> {
    ArrayList<Store.OrderedItem> list;
    final Context context;
    protected class ItemView extends RecyclerView.ViewHolder {
        RadioButton stat;
        TextView quantity;
        TextView name;
        ImageView img;
        public ItemView(@NonNull View itemView) {
            super(itemView);
            stat = itemView.findViewById(R.id.ordered_item_stat);
            quantity = (TextView)itemView.findViewById(R.id.ordered_item_quantity);
            name = (TextView)itemView.findViewById(R.id.ordered_item_name);
            img = (ImageView)itemView.findViewById(R.id.thumb_img);
        }

        public void setQuantity(int quantity){
            this.quantity.setText(String.valueOf(quantity));
        }
        public void setName(String name){
            this.name.setText(name);
        }
        public void setImg(int image_res){
            img.setImageResource(image_res);
        }
        public void setStatus(String stat){this.stat.setText(stat);}
    }
    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_ordered_check,parent,false);
        return new ItemView(itemView);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        Store.OrderedItem currentItem = list.get(position);
        holder.setName(currentItem.name);
        holder.setQuantity(currentItem.quantity);
        holder.setImg(currentItem.img);
        holder.setStatus(currentItem.status);

    }
    public OrderedItemAdapter(Context context, ArrayList<Store.OrderedItem> list){
        this.list=list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

