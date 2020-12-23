package com.team3.fdiosystem.viewmodels;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.Constant;
import com.team3.fdiosystem.activities.DemoActivity;
import com.team3.fdiosystem.activities.MainActivity;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.OrderItemModel;
import com.team3.fdiosystem.models.OrderModel;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.models.UserModel;
import com.team3.fdiosystem.repositories.services.FoodListService;
import com.team3.fdiosystem.repositories.services.FoodService;
import com.team3.fdiosystem.repositories.services.ImageService;
import com.team3.fdiosystem.repositories.services.LocalStorage;
import com.team3.fdiosystem.repositories.services.OrderService;
import com.team3.fdiosystem.repositories.services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoVM extends BaseObservable {
    private String text;
    private Uri imageUrl;

    private Event<Boolean> imgChoseEv = new Event<>();
    public Event getImgChoseEv() {
        return imgChoseEv;
    }

    @Bindable
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
        notifyPropertyChanged(BR.response);
    }

    private String response;
    private OrderService service;

    public DemoVM(){
        service = new OrderService();
    }

    @Bindable
    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, Uri imageUrl) {
        Picasso.get().load(imageUrl).into(view);
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    public void onClick() {
        String data = getText();
        if (data != null && data.length() > 0) {
            //ORDER ---------
            //postRequest();
            //getRequest();

            //FOOD --------------
            //foodGetRequest();
            foodPostRequest();
            //foodPutRequest("BZbOSZs8yue0O9LlAaY2");
            //foodGetRequestById("BZbOSZs8yue0O9LlAaY2");
            //foodDeleteRequest("GrfATrsVgGsSDAepFTDO");
            //foodDeleteRequest(new String[]{"Bm92RIV3YbhK9CMtkhrg", "GrfATrsVgGsSDAepFTDO", "It0grOm1rgmuCYBQ2Irq", "oQMmb7PgWg5mY4sHYtjG"});

            //FOOD LIST ------------
            //foodListGetRequest();
            //foodListGetRequestById("SQOlN86hhvgNNCsPnkW6");
            //foodListPostRequest();
            //foodListPutRequest("tF7BuOdpwkqnaHdQuYWS");
            //foodListDeleteRequest("tF7BuOdpwkqnaHdQuYWS");



            //USER -----------
            //loginRequest(data);
            setText("");

        }

    }

    private void postRequest() {
        OrderItemModel fish = new OrderItemModel("pNYkZOdzPyaa2WFat5eq", 1, "waiting");
        OrderItemModel veggies = new OrderItemModel("ytO0txHHIcBh0SKdhD9v", 2, "waiting");
        OrderItemModel[] list= {fish, veggies};

        String token = LocalStorage.getData(DemoActivity.getContext(), Constant.TOKEN);
        OrderModel order = new OrderModel(
                "da","da","Khong hanh", "12",token,list
        );

        order.setList_order_item(list);
        Log.d("LONG", new Gson().toJson(order));
        service.createOrder(order).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void getRequest(){
        service.getOrders().enqueue(new Callback<OrderModel[]>() {
            @Override
            public void onResponse(Call<OrderModel[]> call, Response<OrderModel[]> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                OrderModel[] data = response.body();
                Log.d("LONG", new Gson().toJson(data));
            }

            @Override
            public void onFailure(Call<OrderModel[]> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodGetRequestById(String id){
        FoodService fService = new FoodService();
        fService.getFoodById(id).enqueue(new Callback<FoodModel>() {
            @Override
            public void onResponse(Call<FoodModel> call, Response<FoodModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                FoodModel data = response.body();
                setResponse(new Gson().toJson(data));
            }

            @Override
            public void onFailure(Call<FoodModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodGetRequest(){
        FoodService fService = new FoodService();
        fService.getFoods().enqueue(new Callback<FoodModel[]>() {
            @Override
            public void onResponse(Call<FoodModel[]> call, Response<FoodModel[]> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                FoodModel[] data = response.body();
                Log.d("LONG", new Gson().toJson(data));
                setResponse(Integer.toString(data.length));
            }

            @Override
            public void onFailure(Call<FoodModel[]> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodPostRequest() {
        FoodService fService = new FoodService();
        FoodModel cheese = new FoodModel("Cheese","./img.png", "Cheese is so delicious", "food", "100000");
        imgChoseEv.trigger();
        fService.createFood(cheese).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodPutRequest(String id) {
        FoodService fService = new FoodService();
        FoodModel cheese = new FoodModel("Cheese","./img.png", "Cheese is horrible, and tasteless", "food", "100000");
        fService.updateFood(id, cheese).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodDeleteRequest(String id) {
        FoodService fService = new FoodService();
        fService.deleteFoodById(id).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodDeleteRequest(String[] ids) {
        FoodService fService = new FoodService();
        fService.deleteFoodByIdList(ids).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void loginRequest(String username) {
        UserService userService = new UserService();

        UserModel user = new UserModel(username, "caonohope");

        userService.login(user).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodListGetRequest(){
        FoodListService fService = new FoodListService();
        fService.getFoodLists().enqueue(new Callback<FoodListModel[]>() {
            @Override
            public void onResponse(Call<FoodListModel[]> call, Response<FoodListModel[]> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                FoodListModel[] data = response.body();
                Log.d("LONG", new Gson().toJson(data));
                setResponse(Integer.toString(data.length));
            }

            @Override
            public void onFailure(Call<FoodListModel[]> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodListGetRequestById(String id){
        FoodListService fService = new FoodListService();
        fService.getFoodListById(id).enqueue(new Callback<FoodListModel>() {
            @Override
            public void onResponse(Call<FoodListModel> call, Response<FoodListModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                FoodListModel data = response.body();
                setResponse(new Gson().toJson(data));
            }

            @Override
            public void onFailure(Call<FoodListModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodListPostRequest() {
        FoodListService fService = new FoodListService();
        FoodListModel beverage = new FoodListModel("Beverage", "img.png");
        beverage.setFoodIdList(new String[]{"123", "456"});
        fService.createFoodList(beverage).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodListPutRequest(String id) {
        FoodListService fService = new FoodListService();
        FoodListModel beverage = new FoodListModel("Drink", "img.png");
        beverage.setFoodIdList(new String[]{});
        fService.updateFoodList(id, beverage).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }

    private void foodListDeleteRequest(String id) {
        FoodListService fService = new FoodListService();
        fService.deleteFoodListById(id).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(!response.isSuccessful()){
                    setText("Error " + response.code());
                    return;
                }

                ResponseModel chat = response.body();
                setText(chat.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                setText(t.getMessage());
            }
        });
    }
}
