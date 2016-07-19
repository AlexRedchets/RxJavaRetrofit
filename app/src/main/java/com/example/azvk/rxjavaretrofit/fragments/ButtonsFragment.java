package com.example.azvk.rxjavaretrofit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.azvk.rxjavaretrofit.Adapters.RecycleViewAdapter;
import com.example.azvk.rxjavaretrofit.Clients.PlayerClient;
import com.example.azvk.rxjavaretrofit.Generator;
import com.example.azvk.rxjavaretrofit.Models.Player;
import com.example.azvk.rxjavaretrofit.R;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ButtonsFragment extends Fragment {

    private static final String TAG = ButtonsFragment.class.getSimpleName();
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buttons, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);

        /*Button get_button = (Button)getView().findViewById(R.id.get_button);
        Button clear_button = (Button)getView().findViewById(R.id.clear_button);



        get_button.setOnClickListener(this::onGetButtonClicked);

        clear_button.setOnClickListener(this::onClearButtonClicked);*/
    }

    private void onGetButtonClicked(View view) {

        Toast.makeText(getActivity(), "GetButtonClicked", Toast.LENGTH_SHORT).show();

        PlayerClient client = Generator.createService(PlayerClient.class);
        Observable<List<Player>> russia_players = client.player("Russia");
        russia_players
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(playerData -> {
                    adapter.updateAdapter(playerData);
                },
                        throwable -> Log.e("Error", throwable.getMessage()));


    }

    private void onClearButtonClicked(View view) {
        Toast.makeText(getActivity(), "ClearButtonClicked", Toast.LENGTH_SHORT).show();
    }

    private void setupView(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        //recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(getActivity()) ;
        recyclerView.setAdapter(adapter);
    }
}
