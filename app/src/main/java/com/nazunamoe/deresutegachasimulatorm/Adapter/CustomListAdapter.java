package com.nazunamoe.deresutegachasimulatorm.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<Card> list = new ArrayList<Card>();
    int num;
    Resources resources;
    ImageView type;
    TextView Rarity;
    Card cardData;
    TextView Name;

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
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.card_adapter,parent,false);
        }

        Name = (TextView) convertView.findViewById(R.id.cardName);
        Rarity = (TextView) convertView.findViewById(R.id.cardRarity);
        TextView Type = (TextView) convertView.findViewById(R.id.cardType);

        type = (ImageView) convertView.findViewById(R.id.typeView);

        cardData = list.get(position);

        Rarity.setText(parent.getResources().getString(R.string.cardRarity)+" : "+cardData.Rarity);
        if(cardData.Rarity == "SS RARE" || cardData.Rarity == "SS RARE+"){
            Rarity.setTextColor(Color.GREEN);
        }else if(cardData.Rarity == "S RARE" || cardData.Rarity == "S RARE+"){
            Rarity.setTextColor(Color.CYAN);
        }else{
            Rarity.setTextColor(Type.getTextColors());
        }
        Type.setText(parent.getResources().getString(R.string.cardType)+" : "+cardData.Type);
        num = cardData.No;

        if(cardData.CardCategory == 2 || cardData.CardCategory == 3){
            Name.setText(parent.getResources().getString(R.string.cardName)+" : "+cardData.CardName+" * ");
            Name.setTextColor(Color.RED);
        }else{
            Name.setText(parent.getResources().getString(R.string.cardName)+" : "+cardData.CardName);
            Name.setTextColor(Rarity.getTextColors());
        }

        resources = convertView.getResources();

        Glide.with(convertView).load("https://hidamarirhodonite.kirara.ca/icon_card/"+cardData.No+".png").into(type);

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
