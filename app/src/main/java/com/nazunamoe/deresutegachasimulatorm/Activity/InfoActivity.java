package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Adapter.CustomListAdapter;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    CustomListAdapter adapter;

    ArrayList<Card> card_list;

    CheckBox cuteonlycheck;
    CheckBox coolonlycheck;
    CheckBox passiononlycheck;

    CheckBox ssronlycheck;
    CheckBox sronlycheck;
    CheckBox ronlycheck;
    CheckBox nonlycheck;

    CheckBox usualonlycheck;
    CheckBox limitedonlycheck;
    CheckBox fesonlycheck;
    CheckBox eventonlycheck;

    boolean cuteonly=false;
    boolean coolonly=false;
    boolean passiononly=false;

    boolean ssronly=false;
    boolean sronly=false;
    boolean ronly=false;
    boolean nonly=false;

    boolean usualonly=false;
    boolean limitedonly=false;
    boolean fesonly=false;
    boolean eventonly=false;

    Toolbar toolbar;
    LinearLayout settings;
    ListView listView;
    CardView listViewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);

        adapter = new CustomListAdapter();
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.CardList);
        listViewCard = (CardView)findViewById(R.id.cardlistcard);
        settings = (LinearLayout)findViewById(R.id.settings);

        listView.setAdapter(adapter);

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("CardList","");
        card_list = gson.fromJson(json, new TypeToken<ArrayList<Card>>(){}.getType());

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                SharedPreferences addSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor prefsEditor = addSharedPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(((Card)adapter.getItem(position)));
                prefsEditor.putString("SelectedCard", json);
                prefsEditor.commit();
                intent = new Intent(getApplicationContext(), CardInfoActivity.class);
                startActivity(intent);
            }
        });

        cuteonlycheck = (CheckBox)findViewById(R.id.CuteOnly);
        coolonlycheck = (CheckBox)findViewById(R.id.CoolOnly);
        passiononlycheck = (CheckBox)findViewById(R.id.PassionOnly);

        ssronlycheck = (CheckBox)findViewById(R.id.SSROnly);
        sronlycheck = (CheckBox)findViewById(R.id.SROnly);
        ronlycheck = (CheckBox)findViewById(R.id.ROnly);
        nonlycheck = (CheckBox)findViewById(R.id.NormalOnly);

        usualonlycheck = (CheckBox)findViewById(R.id.UsualOnly);
        limitedonlycheck = (CheckBox)findViewById(R.id.LimitedOnly);
        fesonlycheck = (CheckBox)findViewById(R.id.FesOnly);
        eventonlycheck = (CheckBox)findViewById(R.id.EventOnly);

        cuteonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cuteonly = check_program(cuteonly);
                updateListbyType();
            }
        });

        coolonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                coolonly = check_program(coolonly);
                updateListbyType();
            }
        });

        passiononlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                passiononly = check_program(passiononly);
                updateListbyType();
            }
        });

        ssronlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ssronly = check_program(ssronly);
                updateListbyType();
            }
        });

        sronlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sronly = check_program(sronly);
                updateListbyType();
            }
        });

        ronlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ronly = check_program(ronly);
                updateListbyType();
            }
        });

        nonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                nonly = check_program(nonly);
                updateListbyType();
            }
        });

        usualonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                usualonly = check_program(usualonly);
                updateListbyType();
            }
        });

        limitedonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                limitedonly = check_program(limitedonly);
                updateListbyType();
            }
        });

        fesonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fesonly = check_program(fesonly);
                updateListbyType();
            }
        });

        eventonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventonly = check_program(eventonly);
                updateListbyType();

            }
        });

        updateListbyType();
    }

    private boolean check_program (boolean check_box) {
        if(check_box){
            check_box=false;
        }else if(!check_box){
            check_box=true;
        }
        return check_box;
    }

    private void updateListbyType(){
        adapter.clearItem();

        for(int i = 0; i< card_list.size(); i++){
            if(cuteonly && card_list.get(i).Type.equals("CUTE")){
                if(updateListbyRarity(card_list.get(i))){
                    adapter.addItem(card_list.get(i));
                }
            }
            if(coolonly && card_list.get(i).Type.equals("COOL")){
                if(updateListbyRarity(card_list.get(i))){
                    adapter.addItem(card_list.get(i));
                }
            }
            if(passiononly && card_list.get(i).Type.equals("PASSION")){
                if(updateListbyRarity(card_list.get(i))){
                    adapter.addItem(card_list.get(i));
                }
            }
            if(!cuteonly && !coolonly && !passiononly){
                if(updateListbyRarity(card_list.get(i))){
                    adapter.addItem(card_list.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private boolean updateListbyRarity(Card input){
        if(ssronly && (input.Rarity.equals("SS RARE") || input.Rarity.equals("SS RARE+"))){
            return updateListbyLimited(input);
        }
        if(sronly && (input.Rarity.equals("S RARE") || input.Rarity.equals("S RARE+"))){
            return updateListbyLimited(input);
        }
        if(ronly && (input.Rarity.equals("RARE") || input.Rarity.equals("RARE+"))){
            return updateListbyLimited(input);
        }
        if(nonly && (input.Rarity.equals("NORMAL") || input.Rarity.equals("NORMAL+"))){
            return updateListbyLimited(input);
        }
        if(!ssronly && !sronly && !ronly && !nonly){
            return updateListbyLimited(input);
        }
        return false;
    }

    private boolean updateListbyLimited(Card input){
        if(usualonly && (!input.Limited && !input.Fes && !input.EventCard)){
            return true;
        }
        if(eventonly && (input.EventCard && (!(input.Rarity.equals("RARE"))||!(input.Rarity.equals("RARE+"))))){
            return true;
        }
        if(fesonly && (input.Limited && input.Fes)){
            return true;
        }
        if(limitedonly && (input.Limited && !input.Fes)){
            return true;
        }
        if(!usualonly && !limitedonly && !fesonly && !eventonly){
            return true;
        }
        return false;
    }

}
