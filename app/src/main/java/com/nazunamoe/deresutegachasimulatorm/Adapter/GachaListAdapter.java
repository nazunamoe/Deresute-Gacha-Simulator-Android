package com.nazunamoe.deresutegachasimulatorm.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class GachaListAdapter extends BaseAdapter {

    private ArrayList<Card> list = new ArrayList<Card>();
    Resources resources;
    ImageView type;
    Card cardData;
    int size;

    public GachaListAdapter(int width) {
        this.size = width / 5 - (width / 10);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gacha_card,parent,false);
        }


        type = (ImageView) convertView.findViewById(R.id.typeView);
        cardData = list.get(position);
        resources = convertView.getResources();

        Glide
                .with(convertView)
                .load("https://hidamarirhodonite.kirara.ca/icon_card/"+cardData.No+".png").into(type);

        return convertView;
    }

    public void addItem(Card input) {
        list.add(input);
    }

    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();

    }
    public void clearItem(){
        list.removeAll(list);
    }
}
