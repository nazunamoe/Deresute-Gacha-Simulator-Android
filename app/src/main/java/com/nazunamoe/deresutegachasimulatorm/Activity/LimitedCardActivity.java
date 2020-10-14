package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Adapter.CustomListAdapter;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LimitedCardActivity extends AppCompatActivity {
    CustomListAdapter adapter;
    ListView listView;
    LinkedHashMap<Integer, Card> card_list;
    Set<Map.Entry<Integer, Card>> card_list_mapset;
    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson;
    String json;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limited_card);
        adapter = new CustomListAdapter();
        listView = findViewById(R.id.limitedCardList);
        toolbar = findViewById(R.id.toolbar4);

        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor = appSharedPrefs.edit();
        gson = new Gson();
        json = appSharedPrefs.getString("CardList","");
        card_list = gson.fromJson(json, new TypeToken<LinkedHashMap<Integer, Card>>(){}.getType());
        card_list_mapset = card_list.entrySet();

        for(int i=0; i<card_list.size(); i++){
            Card temp_card = ((Map.Entry<Integer, Card>)card_list_mapset.toArray()[i]).getValue();
            if(temp_card.RarityInt >= 5 && temp_card.RarityInt % 2 == 1 && temp_card.CardCategory != 1) {
                adapter.addItem(temp_card);
            }
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                Card card2 = (Card)adapter.getItem(position);
                AlertDialog alert_confirm = new AlertDialog.Builder(LimitedCardActivity.this).create();
                if(card2.Availablity == true){
                    card2.Availablity = false;
                    alert_confirm.setTitle(getResources().getString(R.string.SuccessTitle));
                    alert_confirm.setMessage(card2.CardName + getResources().getString(R.string.MoreLimited));
                }else if(card2.Availablity == false){
                    card2.Availablity = true;
                    alert_confirm.setTitle(getResources().getString(R.string.SuccessTitle));
                    alert_confirm.setMessage(card2.CardName + getResources().getString(R.string.NoMoreLimited));
                }
                card_list.replace(card2.No,card2);
                json = gson.toJson(card_list);
                prefsEditor.putString("CardList", json).apply();
                alert_confirm.setButton(Dialog.BUTTON_POSITIVE,getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert_confirm.show();
            }
        });
    }
}
