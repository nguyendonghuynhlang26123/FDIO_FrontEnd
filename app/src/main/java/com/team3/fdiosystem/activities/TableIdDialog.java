package com.team3.fdiosystem.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.databinding.DialogTableSettingBinding;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.repositories.services.LocalStorage;
import com.team3.fdiosystem.viewmodels.Event;

public class TableIdDialog extends DialogFragment {
    String currentTableId;
    Context parentCtx;
    Event onClickEvent;

    public TableIdDialog(String currentTableId, Event submitEv) {
        this.currentTableId = currentTableId;
        this.onClickEvent = submitEv;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parentCtx = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        //Still not has table id => force to enable
        if (currentTableId.equals("")){
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        }
        return dialog;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogTableSettingBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_table_setting,container, false);
        View view = binding.getRoot();

        binding.tableIdEdit.setText(currentTableId);
        binding.tableIdBtn.setOnClickListener(t ->{
            String newTableId = binding.tableIdEdit.getText().toString();
            if (!newTableId.equals("")){
                LocalStorage.saveData(parentCtx, Utils.TABLE_ID,newTableId);
                Store.get_instance().setTableId(newTableId);
                onClickEvent.trigger();
                dismiss();
            }
            else {
                Snackbar.make(view, "Please input table id", Snackbar.LENGTH_LONG);
            }
        });

        return view;
    }
}
