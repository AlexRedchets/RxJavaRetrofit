package com.example.azvk.rxjavaretrofit.fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.azvk.rxjavaretrofit.Clients.PlayerClient;
import com.example.azvk.rxjavaretrofit.Generator;
import com.example.azvk.rxjavaretrofit.Models.Player;
import com.example.azvk.rxjavaretrofit.R;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        Toast.makeText(getActivity(), "GetButtonClicked", Toast.LENGTH_SHORT).show();

        PlayerClient client = Generator.createService(PlayerClient.class);
        Observable<List<Player>> russia_players = client.player("Russia");
        russia_players
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(playerData -> {
                    Log.i(TAG, playerData.get(0).getName());
                },
                        throwable -> Log.e("Error", throwable.getMessage()));


    }

    private void onClearButtonClicked(View view) {
        Toast.makeText(getActivity(), "ClearButtonClicked", Toast.LENGTH_SHORT).show();
    }
}
