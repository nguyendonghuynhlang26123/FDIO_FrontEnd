package com.team3.fdiosystem.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.StorageTask;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.ActivityModifyFormBinding;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.repositories.services.FoodService;
import com.team3.fdiosystem.repositories.services.ImageService;
import com.team3.fdiosystem.viewmodels.FoodDialogVM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDialog extends DialogFragment {

    public interface FoodDialogListener {
        void onSubmit(String msg); //Send message for rerender activity
    }

    // Use this instance of the interface to deliver action events
    FoodDialog.FoodDialogListener listener;
    Context parent;
    ActivityModifyFormBinding binding;
    private StorageTask task;
    private FoodModel targetedModel;
    private String foodListId;

    public FoodDialog (String foodListId, FoodModel model){
        this.foodListId = foodListId;
        targetedModel = new FoodModel(model.getName(),
                model.getThumbnail(),
                model.getDescription(),
                model.getType(),
                model.getPrice());
        targetedModel.setId(model.getId());
    }

    public FoodDialog (String foodListId){
        this.foodListId = foodListId;
        targetedModel = new FoodModel("","","","","");
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = context;
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (FoodDialog.FoodDialogListener) context;
        } catch (ClassCastException e) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_modify_form,container, false);
        View view = binding.getRoot();

        FoodDialogVM vm = new FoodDialogVM(targetedModel);
        vm.setChoosingImgEvent(() -> {
            Intent i = ImageService.fileChoserIntent();
            startActivityForResult(i, ImageService.PICK_IMAGE_REQUEST);
        });
        binding.setVm(vm);

        binding.modifySubmitBtn.setOnClickListener(t -> {
            binding.foodModifyDialog.setVisibility(View.GONE);
            binding.foodProcessDialog.setVisibility(View.VISIBLE);
            handleSubmit();
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== ImageService.PICK_IMAGE_REQUEST && resultCode == -1 //OK STATUS
                && data != null && data.getData() != null){
            Uri imgURI = data.getData();
            binding.getVm().setFoodDialogImg(imgURI.toString());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void handleSubmit(){
        if (targetedModel.getId().equals("")){
            createAFood(binding.getRoot());
        }
        else {
            FoodModel storedModel = Store.get_instance().getFoodModelById(targetedModel.getId());
            if (storedModel != null && !storedModel.isEqual(targetedModel)){
                updateAFood(storedModel);
            }
            else Log.i("SUBMIT", "FOOD not changed");
        }
    }

    private void updateAFood(FoodModel storedModel) {
        FoodService foodService = new FoodService();

        foodService.updateFood(targetedModel.getId(), targetedModel).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("successful")){
                    storedModel.setThumbnail(targetedModel.getThumbnail());
                    storedModel.setName(targetedModel.getName());
                    storedModel.setDescription(targetedModel.getDescription());
                    storedModel.setType(targetedModel.getType());
                    storedModel.setPrice(targetedModel.getPrice());

                    if (listener != null)
                        listener.onSubmit("Update food Successfully");
                }else if (listener != null)
                    listener.onSubmit("Update food Failed! Please try again");
                dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                binding.foodModifyDialog.setVisibility(View.VISIBLE);
                binding.foodProcessDialog.setVisibility(View.GONE);

                Snackbar.make(binding.getRoot(), "Upload Failed", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void createAFood(View view) {
        ImageService is = new ImageService();
        Uri img = Uri.parse(binding.getVm().getFoodDialogImg());
        task = is.uploadFile(img, FoodDialog.this.getActivity().getContentResolver()).addOnSuccessListener(t ->{
            t.getMetadata().getReference().getDownloadUrl().addOnSuccessListener( uri ->{
                //UPload
                callPostAppendApi(view, uri.toString());

            });
        }).addOnFailureListener(t -> {
            binding.foodModifyDialog.setVisibility(View.VISIBLE);
            binding.foodProcessDialog.setVisibility(View.GONE);

            Snackbar.make(view, "Upload Failed", Snackbar.LENGTH_LONG ).show();
        });
    }

    public void callPostAppendApi(View v, String uri){
        FoodService foodService = new FoodService();
        targetedModel.setThumbnail(uri);

        //Call api to create a food first
        foodService.appendFood(foodListId, targetedModel).enqueue(new Callback<ResponseModel>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("successful")){
                    targetedModel.setId(response.body().getId());

                    Store.get_instance().addNewFoodToFoodList(foodListId, targetedModel);

                    if (listener != null)
                        listener.onSubmit("Create food successfully");

                }else if (listener != null)
                    listener.onSubmit("Create food Failed");
                dismiss();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Snackbar.make(v, "Create food list failed! Please try again another time", Snackbar.LENGTH_LONG ).show();
                dismiss();
            }
        });
    }

}
