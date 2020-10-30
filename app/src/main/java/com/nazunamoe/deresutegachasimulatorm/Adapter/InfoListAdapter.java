package com.nazunamoe.deresutegachasimulatorm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nazunamoe.deresutegachasimulatorm.Activity.CardInfoActivity;
import com.nazunamoe.deresutegachasimulatorm.Class.Card;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.ViewHolder> {

    private ArrayList<Card> list;
    private LinkedHashMap<Integer,Card> whole_list;
    private int size;

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    private Context context;

    private Gson gson;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView type;
        public ViewHolder(View v) {
            super(v);

            type = v.findViewById(R.id.typeView);
        }
    }

    public InfoListAdapter(ArrayList<Card> input, int width) {

        this.size = (int)Math.ceil((width / 5) * 0.852);

        this.whole_list = whole_list;
        this.list = input;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.gacha_card, parent, false) ;
        InfoListAdapter.ViewHolder vh = new InfoListAdapter.ViewHolder(view) ;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(holder.itemView.getContext())
                .load("https://hidamarirhodonite.kirara.ca/icon_card/"+list.get(position).No+".png")
                .override(size)
                .into(holder.type);

        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefsEditor = appSharedPrefs.edit();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefsEditor.putInt("SelectedCard",list.get(position).No);
                prefsEditor.commit();
                prefsEditor.putInt("SelectedCardTrained",list.get(position).No + 1);
                prefsEditor.commit();
                Intent intent = new Intent(context, CardInfoActivity.class);
                context.startActivity(intent);
            }
        });

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
