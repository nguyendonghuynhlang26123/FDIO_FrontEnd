package com.team3.fdiosystem.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.activities.MenuActivity;
import com.team3.fdiosystem.models.Item;
import com.team3.fdiosystem.models.ItemGroup;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final ArrayList<Object> data;

    protected static class MenuViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        FloatingActionButton fab;
        CardView cardholder;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.menuItem);
            cardholder = (CardView)itemView.findViewById(R.id.menu_card_holder);
            fab = (FloatingActionButton)itemView.findViewById(R.id.fab);
//            fab.setVisibility(View.INVISIBLE);

        }
        public void setImg(int img){
            this.img.setImageResource(img);
        }
    }

    protected static class FixedMenuViewHolder extends RecyclerView.ViewHolder{
        CardView[] card_holders = new CardView[3];
        ImageView[] images = new ImageView[3];
        public FixedMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            card_holders[0] = (CardView)itemView.findViewById(R.id.fi1_holder);
            card_holders[1] = (CardView)itemView.findViewById(R.id.fi2_holder);
            card_holders[2] = (CardView)itemView.findViewById(R.id.fi3_holder);
            images[0] = (ImageView)itemView.findViewById(R.id.fi1_img);
            images[1] = (ImageView)itemView.findViewById(R.id.fi2_img);
            images[2] = (ImageView)itemView.findViewById(R.id.fi3_img);
        }

    }

    public MenuAdapter(Context context, ArrayList<Object> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0) return 0;
        else return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater itemView = LayoutInflater.from(parent.getContext());

        if(viewType==0){
            View fixedView = itemView.inflate(R.layout.fixed_menu_item,parent,false);
            return new FixedMenuViewHolder(fixedView);
        }
        else {
            View menuView = itemView.inflate(R.layout.recycler_menu_item,parent,false);
            return new MenuViewHolder(menuView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            FixedMenuViewHolder fixed_holder = (FixedMenuViewHolder)holder;
            ItemGroup currentItem = (ItemGroup)data.get(position);
            fixed_holder.images[0].setImageResource(currentItem.item1.img);
            fixed_holder.images[1].setImageResource(currentItem.item2.img);
            fixed_holder.images[2].setImageResource(currentItem.item3.img);
            fixed_holder.card_holders[0].setOnClickListener(v -> {
                Intent i = new Intent(context,MenuActivity.class);
                i.putExtra("type", position);
                context.startActivity(i);
            });
            fixed_holder.card_holders[1].setOnClickListener(v -> {
                Intent i = new Intent(context,MenuActivity.class);
                i.putExtra("type", position);
                context.startActivity(i);
            });
            fixed_holder.card_holders[2].setOnClickListener(v -> {
                Intent i = new Intent(context,MenuActivity.class);
                i.putExtra("type", position);
                context.startActivity(i);
            });
        }
        else {
            Item currentItem = (Item)data.get(position);
            MenuViewHolder menu_holder = (MenuViewHolder)holder;
            menu_holder.setImg(currentItem.img);
            menu_holder.cardholder.setOnClickListener(v -> {
                if(v.isLongClickable()==false){
                    v.clearAnimation();
                    v.setLongClickable(true);
                    v.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
                }
                else{
                    Intent i = new Intent(context, MenuActivity.class);
                    i.putExtra("type", position);
                    context.startActivity(i);
                }
            });
            menu_holder.cardholder.setOnLongClickListener(v -> {
                Animation shake = AnimationUtils.loadAnimation(context,R.anim.shake);
                v.startAnimation(shake);
                v.setLongClickable(false);
                v.findViewById(R.id.fab).setVisibility(View.VISIBLE);
                return true;
            });
            menu_holder.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Object> newList = (ArrayList<Object>) data.clone();
                    newList.remove(position);
                    ItemDiffCallback difcb = new ItemDiffCallback(data, newList);
                    DiffUtil.DiffResult difres = DiffUtil.calculateDiff(difcb);
                    data.remove(position);
//                    notifyDataSetChanged();
                    difres.dispatchUpdatesTo(MenuAdapter.this);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}

