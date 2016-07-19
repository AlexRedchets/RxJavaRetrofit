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
import java.util.List;

import com.example.azvk.rxjavaretrofit.Adapters.RecycleViewAdapter;
import com.example.azvk.rxjavaretrofit.Clients.PlayerClient;
import com.example.azvk.rxjavaretrofit.EventList;
import com.example.azvk.rxjavaretrofit.Generator;
import com.example.azvk.rxjavaretrofit.Models.Player;
import com.example.azvk.rxjavaretrofit.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecycleViewFragment extends Fragment{

    private static final String TAG = RecycleViewFragment.class.getSimpleName();
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycle_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);

    }

    private void setupView(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(getActivity()) ;
        recyclerView.setAdapter(adapter);
    }


    @Subscribe
    public void onEvent(EventList eventList){
        switch (eventList.getResultCode()){
            case 111:
                Log.i(TAG, "GET click received");
                onGetClicked();
                break;
            case 222:
                Log.i(TAG, "CLEAR click received");
                adapter.clearAll();
                break;
        }
    }

    private void onGetClicked(){
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
}
