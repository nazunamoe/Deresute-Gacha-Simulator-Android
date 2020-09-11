package com.nazunamoe.deresutegachasimulatorm.Card;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nazunamoe.deresutegachasimulatorm.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<Card> list = new ArrayList<Card>();
    int num;
    int resourceId;
    Resources resources;
    ImageView type;
    TextView Rarity;
    View con;
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
            convertView = inflater.inflate(R.layout.card,parent,false);
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

        if(cardData.Fes || cardData.Limited){
            Name.setText(parent.getResources().getString(R.string.cardName)+" : "+cardData.CardName+" * ");
            Name.setTextColor(Color.RED);
        }else{
            Name.setText(parent.getResources().getString(R.string.cardName)+" : "+cardData.CardName);
            Name.setTextColor(Rarity.getTextColors());
        }

        resources = convertView.getResources();

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.get().load("https://hidamarirhodonite.kirara.ca/icon_card/"+cardData.No+".png").into(type);
        built.get().setLoggingEnabled(false);

        switch(cardData.Type) {
            case "CUTE": {
                break;
            }
            case "COOL": {
                break;
            }
            case "PASSION": {
                break;
            }
            default: {
                break;
            }
        }
        con = convertView;
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
