package com.team3.fdiosystem.viewmodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.activities.CartActivity;

import java.util.ArrayList;

public class PromotionItemAdapter extends RecyclerView.Adapter<PromotionItemAdapter.ItemView> {
    ArrayList<String> list;
    final Context context;

    public PromotionItemAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }
    protected class ItemView extends RecyclerView.ViewHolder {
        TextView Id;
        CheckBox choose;
        public ItemView(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.promo_item_name);
            choose = itemView.findViewById(R.id.promo_item_checkbox);
        }

    }


    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promotion_item,parent,false);
        return new PromotionItemAdapter.ItemView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        String current_promo_id = list.get(position);
        holder.Id.setText(current_promo_id);
        holder.choose.setOnClickListener(v -> {
            if(holder.choose.isChecked()){
                CartActivity.addPromotion(current_promo_id);
            }
            else{
                CartActivity.removePromotion(current_promo_id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
