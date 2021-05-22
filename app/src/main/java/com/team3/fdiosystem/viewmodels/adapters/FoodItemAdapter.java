package com.team3.fdiosystem.viewmodels.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.activities.FoodDetailActivity;
import com.team3.fdiosystem.databinding.RecyclerItemBinding;
import com.team3.fdiosystem.viewmodels.Event;
import com.team3.fdiosystem.viewmodels.FoodItemVM;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {
    private Context context;
    private List<FoodItemVM> items;

    public FoodItemAdapter(Context context, List<FoodItemVM> items) {
        this.context = context;
        this.items = items;
    }

    public List<FoodItemVM> getItems() {
        return items;
    }

    public void setItems(List<FoodItemVM> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_item, parent, false);
        FoodItemViewHolder viewHolder = new FoodItemViewHolder(itemBinding);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItemVM item = items.get(position);

        holder.itemBinding.setVm(item);
        holder.itemBinding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, FoodDetailActivity.class);
            intent.putExtra("id", item.getId());
            context.startActivity(intent);
        });
        if(position == getItemCount()/2){
            holder.itemBinding.promotionTag.setVisibility(View.VISIBLE);
            long sale = 0;
            try {
                sale = (long) Utils.format.parse(item.getPrice())/2;
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            int sale = Integer.parseInt(item.getPrice())/2;
            holder.itemBinding.menuItemPrice.setText(Utils.format.format(sale));
            holder.itemBinding.menuItemPrice.setTextColor(context.getResources().getColor(R.color.red));
        } else holder.itemBinding.promotionTag.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemBinding itemBinding;

        public FoodItemViewHolder(@NonNull RecyclerItemBinding itemView ) {
            super(itemView.getRoot());
            itemBinding = itemView;
        }
    }
}
