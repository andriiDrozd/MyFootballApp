package com.example.footballapp.util;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.footballapp.R;

public class NetworkChangeListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!Common.isInternetConnected(context)){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            View viewDialog= LayoutInflater.from(context).inflate(R.layout.check_internet_dialog, null);
            builder.setView(viewDialog);

            AppCompatButton buttonRetry=viewDialog.findViewById(R.id.buttonRetry);

            AlertDialog dialog=builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);
            buttonRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    onReceive(context,intent);
                }
            });
        }
    }
}

interface NetworkCallback{
    void onConnect();
    void onDisconnect();
}