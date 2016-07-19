package com.example.azvk.rxjavaretrofit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.example.azvk.rxjavaretrofit.Models.Player;
import com.example.azvk.rxjavaretrofit.R;
import com.squareup.picasso.Picasso;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{

    private List<Player> listPlayer;
    private Context context;
    private String TAG = RecycleViewAdapter.class.getSimpleName();

    public RecycleViewAdapter(Context context) {
        this.context = context;
    }

    public void updateAdapter(List<Player> lists){
        listPlayer = lists;
        notifyDataSetChanged();
        Log.i(TAG, "Adapter is updated");
    }

    public Context getContext(){
        return context;
    }

    public void clearAll(){
        listPlayer.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listPlayer != null ? listPlayer.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Player currentPlayerData = listPlayer.get(position);

        holder.playerName.setText(currentPlayerData.getName());
        Picasso.with(context).load(currentPlayerData.getImgRes()).into(holder.playerImage);
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder{

        TextView playerName;
        ImageView playerImage;

        private ViewHolder(View itemView) {
            super(itemView);

            playerImage = (ImageView)itemView.findViewById(R.id.playerImage);
            playerName = (TextView)itemView.findViewById(R.id.playerName);
        }
    }

}