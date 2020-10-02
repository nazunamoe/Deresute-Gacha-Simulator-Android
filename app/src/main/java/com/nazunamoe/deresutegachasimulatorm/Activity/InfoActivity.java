package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Adapter.InfoListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class InfoActivity extends AppCompatActivity {
    InfoListAdapter adapter;

    LinkedHashMap<Integer, Card> card_list;
    Set<Map.Entry<Integer, Card>> card_list_mapset;

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
    RecyclerView recyclerView;
    CardView listViewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("TempCardList","");
        card_list = gson.fromJson(json, new TypeToken<LinkedHashMap<Integer, Card>>(){}.getType());
        card_list_mapset = card_list.entrySet();

        adapter = new InfoListAdapter(card_list, new ArrayList<>(card_list.keySet()), width);
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.CardList);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 5, RecyclerView.VERTICAL, false));
        listViewCard = findViewById(R.id.cardlistcard);
        settings = findViewById(R.id.settings);

        recyclerView.setAdapter(adapter);

        cuteonlycheck = findViewById(R.id.CuteOnly);
        coolonlycheck = findViewById(R.id.CoolOnly);
        passiononlycheck = findViewById(R.id.PassionOnly);

        ssronlycheck = findViewById(R.id.SSROnly);
        sronlycheck = findViewById(R.id.SROnly);
        ronlycheck = findViewById(R.id.ROnly);
        nonlycheck = findViewById(R.id.NormalOnly);

        usualonlycheck = findViewById(R.id.UsualOnly);
        limitedonlycheck = findViewById(R.id.LimitedOnly);
        fesonlycheck = findViewById(R.id.FesOnly);
        eventonlycheck = findViewById(R.id.EventOnly);

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
        Map.Entry<Integer, Card> elementAt;
        for(int i = 0; i< card_list_mapset.size(); i++){
            elementAt = (Map.Entry<Integer, Card>) card_list_mapset.toArray()[i];
            if(elementAt.getValue().No % 2 == 1) {
                if(cuteonly && elementAt.getValue().No / 100000 == 1){
                    if(updateListbyRarity(elementAt.getValue())){
                        adapter.addItem(elementAt.getValue());
                    }
                }
                if(coolonly && elementAt.getValue().No / 100000 == 2){
                    if(updateListbyRarity(elementAt.getValue())){
                        adapter.addItem(elementAt.getValue());
                    }
                }
                if(passiononly && elementAt.getValue().No / 100000 == 3){
                    if(updateListbyRarity(elementAt.getValue())){
                        adapter.addItem(elementAt.getValue());
                    }
                }
                if(!cuteonly && !coolonly && !passiononly){
                    if(updateListbyRarity(elementAt.getValue())){
                        adapter.addItem(elementAt.getValue());
                    }
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
