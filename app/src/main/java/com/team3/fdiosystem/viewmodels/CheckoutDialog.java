package com.team3.fdiosystem.viewmodels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.team3.fdiosystem.R;

public class CheckoutDialog extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.checkout_dialog,container, false);
        return view;
    }

    public CheckoutDialog(){}
}
