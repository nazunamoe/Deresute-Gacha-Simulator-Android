package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import com.nazunamoe.deresutegachasimulatorm.Card.CustomListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Database.DatabaseHelper;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class LimitedCardActivity extends AppCompatActivity {
    CustomListAdapter adapter;
    ListView listView;
    ArrayList<Card> wholelist;
    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limited_card);
        adapter = new CustomListAdapter();
        listView = (ListView)findViewById(R.id.gachacardlist);
        toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        prefsEditor = appSharedPrefs.edit();
        gson = new Gson();
        String json = appSharedPrefs.getString("CardList","");
        wholelist = gson.fromJson(json, new TypeToken<ArrayList<Card>>(){}.getType());

        for(int i=0; i<wholelist.size(); i++){
            adapter.addItem(wholelist.get(i));
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                final View view2 = view;
                Card card2 = wholelist.get(position);
                AlertDialog alert_confirm = new AlertDialog.Builder(LimitedCardActivity.this).create();
                if(card2.Availablity){
                    card2.Availablity = false;
                    alert_confirm.setTitle(getResources().getString(R.string.SuccessTitle));
                    alert_confirm.setMessage(getResources().getString(R.string.MoreLimited));
                }else{
                    card2.Availablity = true;
                    alert_confirm.setTitle(getResources().getString(R.string.SuccessTitle));
                    alert_confirm.setMessage(getResources().getString(R.string.NoMoreLimited));
                }
                System.out.println(card2.CardName+"="+card2.Availablity);
                wholelist.set(position,card2);
                String json = gson.toJson(wholelist);
                prefsEditor.putString("CardList", json);
                prefsEditor.commit();
                alert_confirm.setButton(Dialog.BUTTON_POSITIVE,getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert_confirm.show();
            }
        });
    }
}
