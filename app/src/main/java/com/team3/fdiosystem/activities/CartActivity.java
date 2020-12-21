package com.team3.fdiosystem.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.models.Promotion;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.CartAdapter;
import com.team3.fdiosystem.viewmodels.CheckoutDialog;
import com.team3.fdiosystem.viewmodels.PromotionItemAdapter;

import java.util.ArrayList;

public class CartActivity extends FragmentActivity {

    private static ArrayList<String> current_promos = new ArrayList<>();
    int total_price=0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ArrayList<CartItem> cart = getCart();
        setPrice(cart);
        CartAdapter adapter = new CartAdapter(this, cart);
        LinearLayoutManager cartLayout = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView view = findViewById(R.id.cart_recycler_view);
        view.setAdapter(adapter);
        view.setLayoutManager(cartLayout);
        Button ckout = findViewById(R.id.ckoutBtn);
        ckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new CheckoutDialog(total_price, current_promos);
                dialog.show(getSupportFragmentManager(),"Checkout");
            }
        });
        TextView promo = findViewById(R.id.ckoutPromo);
        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMaterialDialog(R.string.promo_label, Store.get_instance().getPromos());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setPrice(ArrayList<CartItem> cart){
        cart.forEach(item->{
            total_price+=item.price;
        });
    }

    public void updatePrice(){

    }

    private ArrayList<CartItem> getCart(){
        ArrayList<CartItem> cart = new ArrayList<>(10);
        for(int i=0; i<10;++i){
            cart.add(new CartItem(i,R.drawable.cfsua_bg,i));
            updatePrice();
        }
        return cart;
    }
    public static void addPromotion(String p){
        current_promos.add(p);
    }
    public static void removePromotion(String p){
        current_promos.remove(p);
    }
    public void createMaterialDialog(final int extra_title, ArrayList<Promotion> checkbox_list) {

        ArrayList<String> checkbox_content= new ArrayList<>();
        for(int i=0;i<checkbox_list.size();++i) checkbox_content.add(checkbox_list.get(i).getID());
        new MaterialDialog.Builder(this)
                .title(extra_title).adapter(new PromotionItemAdapter(this,checkbox_content),null)
                .alwaysCallMultiChoiceCallback()
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        /**
                         * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected check box to actually be selected.
                         * See the limited multi choice dialog example in the sample project for details.
                         **/
                        return true;
                    }
                })
                .positiveText("Choose")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

//
                    }
                })
                .show();
    }

}