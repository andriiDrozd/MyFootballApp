package com.example.footballapp;


import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;


import com.example.footballapp.util.NetworkCallback;
import com.example.footballapp.util.NetworkChangeListener;

public class MainActivity extends AppCompatActivity implements NetworkCallback {

    NetworkChangeListener networkChangeListener ;
Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.main_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

        @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }


    @SuppressLint("ResourceType")
    @Override
    public void onConnect() {

        NavHostController navController = new NavHostController(this);
        navController.setGraph(R.id.nav_graph);

    }

    @Override
    public void onDisconnect() {

    }
}