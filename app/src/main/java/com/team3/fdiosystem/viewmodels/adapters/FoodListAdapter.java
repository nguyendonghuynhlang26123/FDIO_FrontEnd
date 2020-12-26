package com.team3.fdiosystem.viewmodels.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.ui.UI_FoodListItem;

import java.util.ArrayList;

public class FoodListAdapter extends ArrayAdapter {
    public FoodListAdapter(Activity context, ArrayList<UI_FoodListItem> menuList){
        super(context,0,menuList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_menu_item,parent,false);
        }

        ImageView img = listItemView.findViewById(R.id.menuItem);
        UI_FoodListItem current_item = (UI_FoodListItem)getItem(position);
        Picasso.get().load(current_item.getImg()).into(img);

        FloatingActionButton fab = listItemView.findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        listItemView.setLongClickable(true);
        fab.setOnClickListener(v -> {
            //Store.get_instance().removeMenu(current_item);
            notifyDataSetChanged();
        });
//        TextView txt = listItemView.findViewById(R.id.menu_txt);
//        txt.setText(current_item.getTxtsrc());
        return listItemView;
    }
}
