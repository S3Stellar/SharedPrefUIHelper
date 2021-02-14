package com.example.sharedprefui;

import android.os.Bundle;

import com.example.mysharedprefui.MySharedPrefActivity;


public class MainActivity extends MySharedPrefActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //startActivity(new Intent(this, MySharedPrefActivity.class));
    }
}