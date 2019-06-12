package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Card.CustomListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Database.DatabaseHelper;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.io.IOException;
import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    CustomListAdapter adapter;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    ArrayList<Card> wholelist;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        adapter = new CustomListAdapter();

        ListView listView = (ListView)findViewById(R.id.CardList);
        listView.setAdapter(adapter);
        mDBHelper = new DatabaseHelper(this);

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
                if(isChecked){
                    cuteonly=true;
                }else if(!isChecked){
                    cuteonly=false;
                }
                updateListbyType();
            }
        });

        coolonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    coolonly=true;
                }else if(!isChecked){
                    coolonly=false;
                }
                updateListbyType();
            }
        });

        passiononlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    passiononly=true;
                }else if(!isChecked){
                    passiononly=false;
                }
                updateListbyType();
            }
        });

        ssronlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ssronly=true;
                }else if(!isChecked){
                    ssronly=false;
                }
                updateListbyType();
            }
        });

        sronlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sronly=true;
                }else if(!isChecked){
                    sronly=false;
                }
                updateListbyType();
            }
        });

        ronlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ronly=true;
                }else if(!isChecked){
                    ronly=false;
                }
                updateListbyType();
            }
        });

        nonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    nonly=true;
                }else if(!isChecked){
                    nonly=false;
                }
                updateListbyType();
            }
        });

        //

        usualonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    usualonly=true;
                }else if(!isChecked){
                    usualonly=false;
                }
                updateListbyType();
            }
        });

        limitedonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    limitedonly=true;
                }else if(!isChecked){
                    limitedonly=false;
                }
                updateListbyType();
            }
        });

        fesonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    fesonly=true;
                }else if(!isChecked){
                    fesonly=false;
                }
                updateListbyType();
            }
        });

        eventonlycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    eventonly=true;
                }else if(!isChecked){
                    eventonly=false;
                }
                updateListbyType();
            }
        });

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        mDBHelper.openDataBase();

        wholelist = mDBHelper.getAllCardList();

        for(int i=0; i<wholelist.size(); i++){
            adapter.addItem(wholelist.get(i).No,wholelist.get(i).CardName,wholelist.get(i).Rarity,wholelist.get(i).Type);
        }
    }

    private void updateListbyType(){
        adapter.clearItem();
        for(int i=0; i<wholelist.size(); i++){
            if(cuteonly && wholelist.get(i).Type=="CUTE"){
                if(updateListbyRarity(wholelist.get(i))){
                    adapter.addItem(wholelist.get(i).No,wholelist.get(i).CardName,wholelist.get(i).Rarity,wholelist.get(i).Type);
                }
            }
            if(coolonly && wholelist.get(i).Type=="COOL"){
                if(updateListbyRarity(wholelist.get(i))){
                    adapter.addItem(wholelist.get(i).No,wholelist.get(i).CardName,wholelist.get(i).Rarity,wholelist.get(i).Type);
                }
            }
            if(passiononly && wholelist.get(i).Type=="PASSION"){
                if(updateListbyRarity(wholelist.get(i))){
                    adapter.addItem(wholelist.get(i).No,wholelist.get(i).CardName,wholelist.get(i).Rarity,wholelist.get(i).Type);
                }
            }
            if(!cuteonly && !coolonly && !passiononly){
                if(updateListbyRarity(wholelist.get(i))){
                    adapter.addItem(wholelist.get(i).No,wholelist.get(i).CardName,wholelist.get(i).Rarity,wholelist.get(i).Type);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private boolean updateListbyRarity(Card input){
        if(ssronly && (input.Rarity == "SS RARE" || input.Rarity == "SS RARE+")){
            return updateListbyLimited(input);
        }
        if(sronly && (input.Rarity == "S RARE" || input.Rarity == "S RARE+")){
            return updateListbyLimited(input);
        }
        if(ronly && (input.Rarity == "RARE" || input.Rarity == "RARE+")){
            return updateListbyLimited(input);
        }
        if(nonly && (input.Rarity == "NORMAL" || input.Rarity == "NORMAL+")){
            return updateListbyLimited(input);
        }
        if(!ssronly && !sronly && !ronly && !nonly){
            return updateListbyLimited(input);
        }
        return false;
    }

    private boolean updateListbyLimited(Card input){
        if(usualonly && (!input.Limited && !input.Fes)){
            return true;
        }
        if(eventonly && (input.EventCard && (input.Rarity !="RARE"||input.Rarity!="RARE+"))){
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
