
package com.team3.fdiosystem.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Build;
import android.os.Bundle;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.ActivityOrderCheckBinding;
import com.team3.fdiosystem.models.OrderItemModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.OrderCheckItemVM;
import com.team3.fdiosystem.viewmodels.adapters.OrderCheckAdapter;

import java.util.ArrayList;

public class OrderedCheckActivity extends AppCompatActivity {
    ActivityOrderCheckBinding binding;
    OrderCheckAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_check);
        binding.recyclerview.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));

        adapter = new OrderCheckAdapter(this,new ArrayList<>());
        binding.recyclerview.setAdapter(adapter);

        loadDataFromStore();
    }

    public void loadDataFromStore() {
        ArrayList<OrderItemModel> orderedList = Store.get_instance().getOrderedList();
        ArrayList<OrderCheckItemVM> vms = new ArrayList<>();
        for (OrderItemModel orderItemModel : orderedList) {
            vms.add(new OrderCheckItemVM(orderItemModel));
        }
        adapter.setVms(vms);
    }
}