package com.example.sunpy.alwaysontop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartTopView(View v){
        startService(new Intent(this, TopService.class));
    }

    public void StopTopView(View v){
        stopService(new Intent(this, TopService.class));
    }


}
