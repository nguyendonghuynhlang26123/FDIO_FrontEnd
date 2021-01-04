package com.team3.fdiosystem.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.team3.fdiosystem.viewmodels.Event;

public class ConfirmDialog extends DialogFragment {
    private String msg;
    Event callBackEvent;


    public ConfirmDialog(String msg, Event callBackEvent ) {
        this.msg = msg;
        this.callBackEvent = callBackEvent;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg)
                .setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, id) -> {
                    if (callBackEvent != null) callBackEvent.trigger();
                    dismiss();
                })
                .setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, id) -> {
                    dismiss();
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
