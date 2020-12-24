package com.team3.fdiosystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.team3.fdiosystem.R;

public class MenuModifyDialog extends DialogFragment {
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(MenuModifyDialog dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_menu_dialog,container, false);
        TextView col = view.findViewById(R.id.col);
        TextView row = view.findViewById(R.id.row);
        Button colIncBtn = view.findViewById(R.id.colIncBtn);
        colIncBtn.setOnClickListener(v -> col.setText(String.valueOf(Integer.parseInt(col.getText().toString())+1)));
        Button colDecBtn = view.findViewById(R.id.colDecBtn);
        colDecBtn.setOnClickListener(v -> col.setText(String.valueOf(Integer.parseInt(col.getText().toString())-1)));
        Button rowIncBtn = view.findViewById(R.id.rowIncBtn);
        rowIncBtn.setOnClickListener(v -> row.setText(String.valueOf(Integer.parseInt(row.getText().toString())+1)));
        Button rowDecBtn = view.findViewById(R.id.rowDecBtn);
        rowDecBtn.setOnClickListener(v -> row.setText(String.valueOf(Integer.parseInt(row.getText().toString())-1)));
        Button orderBtn = view.findViewById(R.id.order_btn);
        orderBtn.setOnClickListener(v -> {
            listener.onDialogPositiveClick(MenuModifyDialog.this);
            MenuModifyDialog.this.dismiss();
        });
        return view;
    }
}

