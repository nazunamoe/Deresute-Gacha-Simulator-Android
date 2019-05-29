package com.nazunamoe.deresutegachasimulatorm.Card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<GachaCardData> list = new ArrayList<GachaCardData>();

    public CustomListAdapter(){

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
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.card,parent,false);
        }

        TextView Name = (TextView) convertView.findViewById(R.id.cardName);
        TextView Rarity = (TextView) convertView.findViewById(R.id.cardRarity);
        TextView Type = (TextView) convertView.findViewById(R.id.cardType);

        ImageView type = (ImageView) convertView.findViewById(R.id.typeView);

        GachaCardData cardData = list.get(position);

        Name.setText(parent.getResources().getString(R.string.cardName)+" : "+cardData.name);
        Rarity.setText(parent.getResources().getString(R.string.cardRarity)+" : "+cardData.rarity);
        Type.setText(parent.getResources().getString(R.string.cardType)+" : "+cardData.type);

        switch(cardData.type){
            case "CUTE":{
                type.setImageResource(R.drawable.cinde_markcute);
                break;
            }
            case "COOL":{
                type.setImageResource(R.drawable.cinde_markcool);
                break;
            }
            case "PASSION":{
                type.setImageResource(R.drawable.cinde_markpassion);
                break;
            }
            default:{
                break;
            }
        }

        return convertView;
    }

    public void addItem(String cardName, String cardRarity, String cardType) {
        GachaCardData newCard = new GachaCardData(cardName,cardRarity,cardType);
        list.add(newCard);
    }

    public void clearItem(){
        list.removeAll(list);
    }
}
