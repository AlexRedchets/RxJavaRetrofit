package com.example.azvk.rxjavaretrofit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.azvk.rxjavaretrofit.EventList;
import com.example.azvk.rxjavaretrofit.R;

import org.greenrobot.eventbus.EventBus;


public class ButtonsFragment extends Fragment {

    private static final String TAG = ButtonsFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buttons, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button get_button = (Button)getView().findViewById(R.id.get_button);
        Button clear_button = (Button)getView().findViewById(R.id.clear_button);

        get_button.setOnClickListener(this::onGetButtonClicked);

        clear_button.setOnClickListener(this::onClearButtonClicked);
    }


    private void onGetButtonClicked(View view) {

        Log.i(TAG, "onGetButtonClicked");

        EventList eventList = new EventList();
        eventList.setResultCode(111);
        EventBus.getDefault().post(eventList);
    }

    private void onClearButtonClicked(View view) {

        Log.i(TAG, "onClearButtonClicked");

        EventList eventList = new EventList();
        eventList.setResultCode(222);
        EventBus.getDefault().post(eventList);
    }
}
