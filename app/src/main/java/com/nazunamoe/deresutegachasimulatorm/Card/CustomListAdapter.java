package com.nazunamoe.deresutegachasimulatorm.Card;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

        Resources resources = convertView.getResources();
        final int resourceId = resources.getIdentifier("card_"+cardData.No, "drawable",
                context.getPackageName());

        type.setImageResource(resourceId);

        switch(cardData.type){
            case "CUTE":{
                break;
            }
            case "COOL":{
                break;
            }
            case "PASSION":{
                break;
            }
            default:{
                break;
            }
        }

        return convertView;
    }

    public void addItem(int no,String cardName, String cardRarity, String cardType) {
        GachaCardData newCard = new GachaCardData(no,cardName,cardRarity,cardType);
        list.add(newCard);
    }

    public void clearItem(){
        list.removeAll(list);
    }
}
