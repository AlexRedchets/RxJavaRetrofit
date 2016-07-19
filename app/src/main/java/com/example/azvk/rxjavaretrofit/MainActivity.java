package com.example.azvk.rxjavaretrofit;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.azvk.rxjavaretrofit.fragments.ButtonsFragment;
import com.example.azvk.rxjavaretrofit.fragments.RecycleViewFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.buttonFrame, new ButtonsFragment())
                .add(R.id.recycleViewFrame, new RecycleViewFragment())
                .commit();
    }
}
