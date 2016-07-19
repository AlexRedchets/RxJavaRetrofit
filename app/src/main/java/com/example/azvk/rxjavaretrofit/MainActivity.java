package com.example.azvk.rxjavaretrofit;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.azvk.rxjavaretrofit.fragments.ButtonsFragment;
import com.example.azvk.rxjavaretrofit.fragments.RecycleViewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.buttonFrame, new ButtonsFragment())
                .replace(R.id.recycleViewFrame, new RecycleViewFragment())
                .commit();
    }
}
