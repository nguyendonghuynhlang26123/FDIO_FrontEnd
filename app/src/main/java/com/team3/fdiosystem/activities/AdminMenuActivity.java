package com.team3.fdiosystem.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.repositories.services.FoodListService;
import com.team3.fdiosystem.viewmodels.Event;
import com.team3.fdiosystem.viewmodels.MenuHompageVM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMenuActivity extends AppCompatActivity implements MenuModifyDialog.NoticeDialogListener {
    MenuHompageVM vm;
    AsymmetricGridViewAdapter menuAdapter;
    AsymmetricGridView menuView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = new MenuHompageVM(this);

        menuView = findViewById(R.id.Gridview);
        menuView.setRequestedColumnWidth(Utils.dpToPx(this,120));
        menuAdapter = new AsymmetricGridViewAdapter(this, menuView, vm.getMenuAdapter());
        menuView.setAdapter(menuAdapter);
        menuView.setAllowReordering(true);
        menuView.isAllowReordering();

        findViewById(R.id.add_fab).setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            DialogFragment dialog = new MenuModifyDialog();
            dialog.show(fragmentManager,"MenuModifier");
        });

        menuView.setOnItemClickListener((parent, view, position, id) -> {
            if(view.isLongClickable()==false){
                view.clearAnimation();
                view.setLongClickable(true);
                view.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            }
            else{
                Intent i = new Intent(AdminMenuActivity.this,AdminMenuContentActivity.class);
                i.putExtra("id", vm.getFoodListId(position));
                startActivity(i);
            }
        });

        menuView.setOnItemLongClickListener((parent, view, position, id) -> {
            Animation shake = AnimationUtils.loadAnimation(AdminMenuActivity.this,R.anim.shake);
            view.startAnimation(shake);
            view.setLongClickable(false);
            FloatingActionButton btn = view.findViewById(R.id.fab);
            btn.setVisibility(View.VISIBLE);
            if (!btn.hasOnClickListeners()){
                btn.setOnClickListener(t -> {
                    Event callBackEv = () -> {
                        removeAnFoodList(position);
                    };
                    DialogFragment dialog = new ConfirmDialog("Delete this food list?", callBackEv);
                    dialog.show(getSupportFragmentManager(),"Confirm");
                });
            };
            return true;
        });

    }

    void refreshRendering(){
        vm = new MenuHompageVM(AdminMenuActivity.this);
        menuAdapter = new AsymmetricGridViewAdapter(this, menuView, vm.getMenuAdapter());
        menuView.setAdapter(menuAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void removeAnFoodList(int position){
        String id = Store.get_instance().getMenu().get(position).getId();


        //TODO: SEND RETROFIT
        FoodListService service = new FoodListService();
        service.deleteFoodListById(id).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("successful")){
                    Store.get_instance().removeAMenu(position);

                    Snackbar.make(menuView, "Successfully delete", Snackbar.LENGTH_LONG).show();
                    refreshRendering();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onFinished() {
        refreshRendering();

    }

}
