package com.nazunamoe.deresutegachasimulatorm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nazunamoe.deresutegachasimulatorm.Activity.MainActivity;
import com.nazunamoe.deresutegachasimulatorm.Class.Card;
import com.nazunamoe.deresutegachasimulatorm.Class.Gacha_Season;
import com.nazunamoe.deresutegachasimulatorm.Database.DatabaseHelper;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class GachaSeasonListAdapter extends RecyclerView.Adapter<GachaSeasonListAdapter.ViewHolder> {

    private ArrayList<Gacha_Season> list;
    Gacha_Season season;
    Context context;

    InfoListAdapter Gacha_Season_Card_List_Adapter;
    DatabaseHelper databaseHelper;
    int Width;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Gacha_Season_Title;
        TextView Gacha_Season_Date;
        RecyclerView Gacha_Season_Card_List;

        public ViewHolder(View v) {
            super(v);
            Gacha_Season_Title = v.findViewById(R.id.gacha_season_title);
            Gacha_Season_Date = v.findViewById(R.id.gacha_season_date);
            Gacha_Season_Card_List = v.findViewById(R.id.gacha_season_card_list);
        }
    }

    public GachaSeasonListAdapter(ArrayList<Gacha_Season> input, Context context, int width, DatabaseHelper database) {
        this.list = input;
        this.context = context;
        this.Width = width;
        this.databaseHelper = database;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.gacha_season_card, parent, false) ;
        GachaSeasonListAdapter.ViewHolder vh = new GachaSeasonListAdapter.ViewHolder(view) ;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        season = list.get(position);
        holder.Gacha_Season_Title.setText(season.Gacha_season_title);
        holder.Gacha_Season_Date.setText(MainActivity.getResourses().getText(R.string.gacha_season_date_title) + " " + season.Gacha_season_start_date + " ~ " + season.Gacha_season_end_date);

        holder.Gacha_Season_Card_List.setLayoutManager(new GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, false));
        Gacha_Season_Card_List_Adapter = new InfoListAdapter(season.Gacha_season_card_list, Width);
        holder.Gacha_Season_Card_List.setAdapter(Gacha_Season_Card_List_Adapter);

        CheckBox t = holder.itemView.findViewById(R.id.gacha_season_checkbox);

        if(season.Availability){
            t.setChecked(true);
        }

        System.out.println(t.isChecked());
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                season = list.get(position);
                int limited;
                if(((CheckBox)v).isChecked()) limited = 0;
                else limited = 1;
                for(Card e : season.Gacha_season_card_list) databaseHelper.setCardLimited(e, limited);
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

    public void addItem(Gacha_Season input) {
            list.add(input);
            System.out.println("dsadsa"+input.Gacha_season_title);
        this.notifyDataSetChanged();
    }

    public void clearItem(){
        list.removeAll(list);
    }
}
