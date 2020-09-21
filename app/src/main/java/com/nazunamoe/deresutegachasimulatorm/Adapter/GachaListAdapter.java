package com.nazunamoe.deresutegachasimulatorm.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Gacha.Gacha;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class GachaListAdapter extends RecyclerView.Adapter<GachaListAdapter.ViewHolder> {

    private ArrayList<Card> list;
    Resources resources;
    Card cardData;
    int size;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView type;
        public ViewHolder(View v) {
            super(v);
            type = v.findViewById(R.id.typeView);
        }
    }

    public GachaListAdapter(ArrayList<Card> input, int width) {
        this.size = (int)Math.ceil((width / 5) * 0.84);
        list = input;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.gacha_card, parent, false) ;
        GachaListAdapter.ViewHolder vh = new GachaListAdapter.ViewHolder(view) ;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load("https://hidamarirhodonite.kirara.ca/icon_card/"+list.get(position).No+".png")
                .override(size)
                .into(holder.type);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Card input) {
        list.add(input);
        this.notifyDataSetChanged();
    }

    public void clearItem(){
        list.removeAll(list);
    }
}
