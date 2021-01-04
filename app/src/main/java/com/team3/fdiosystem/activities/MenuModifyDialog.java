package com.team3.fdiosystem.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.StorageTask;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.AddMenuDialogBinding;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.repositories.services.FoodListService;
import com.team3.fdiosystem.repositories.services.ImageService;
import com.team3.fdiosystem.viewmodels.MenuModifyDialogVM;

import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuModifyDialog extends DialogFragment {
    public interface NoticeDialogListener {
        void onFinished();
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;
    Context parent;
    AddMenuDialogBinding binding;
    private StorageTask task;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = context;
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.add_menu_dialog,container, false);
        View view = binding.getRoot();

        MenuModifyDialogVM vm = new MenuModifyDialogVM();
        binding.setVm(vm);

        imgChoosingHandle();

        procceedBtnHandler(view);
        return view;
    }

    void imgChoosingHandle(){
        binding.addFoodListImgUpload.setOnClickListener(v -> {
            Intent i = ImageService.fileChoserIntent();
            startActivityForResult(i, ImageService.PICK_IMAGE_REQUEST);
        });
    }

    void procceedBtnHandler(View view) {
        binding.addFoodListProceedBtn.setOnClickListener(v -> {
            if (binding.getVm().getFoodListDialogImage().equals("") ||
                    binding.getVm().getFoodListDialogName().equals("")) {
                Snackbar.make(view, "Please fill all input field", Snackbar.LENGTH_LONG ).show();
                return;
            };
            binding.addFoodListContentLayout.setVisibility(View.GONE);
            binding.addFoodListProgressLayout.setVisibility(View.VISIBLE);

            uploadHandle(view);
        });
    }

    private void uploadHandle(View view) {
        ImageService is = new ImageService();
        Uri img = Uri.parse(binding.getVm().getFoodListDialogImage());
        task = is.uploadFile(img, MenuModifyDialog.this.getActivity().getContentResolver()).addOnSuccessListener(t ->{
            t.getMetadata().getReference().getDownloadUrl().addOnSuccessListener( uri ->{
                //UPload
                createOnServerAndQuit(view, uri.toString());


            });
        }).addOnFailureListener(t -> {
            binding.addFoodListContentLayout.setVisibility(View.VISIBLE);
            binding.addFoodListProgressLayout.setVisibility(View.GONE);

            Snackbar.make(view, "Upload Failed", Snackbar.LENGTH_LONG ).show();
        }).addOnProgressListener(snapshot->{
            double process = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
            binding.getVm().setFoodListUploadPercent((int) process);
        });
    }

    public void createOnServerAndQuit(View v, String uri){
        FoodListModel model = binding.getVm().exportFoodListValue(uri);

        FoodListService foodListService = new FoodListService();
        foodListService.createFoodList(model).enqueue(new Callback<ResponseModel>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("successful")){
                    model.setId(response.body().getId());
                    Log.i("CREATE", response.body().getId());
                    Store.get_instance().addAMenu(model);
                    Snackbar.make(v, "Create food list successfully!", Snackbar.LENGTH_LONG ).show();
                }
                else
                    Snackbar.make(v, "Create foodlist failed! Please try again another time", Snackbar.LENGTH_LONG ).show();

                if (listener != null)
                    listener.onFinished();
                dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Snackbar.make(v, "Create food list failed! Please try again another time", Snackbar.LENGTH_LONG ).show();
                dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== ImageService.PICK_IMAGE_REQUEST && resultCode == -1 //OK STATUS
                && data != null && data.getData() != null){
            Uri imgURI = data.getData();
            binding.getVm().setFoodListDialogImage(imgURI.toString());
        }
    }
}