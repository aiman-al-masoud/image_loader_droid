package com.luxlunaris.imageloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImportImageFragment frag = ImportImageFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add( R.id.main_layout,frag, null).commit();


    }
}