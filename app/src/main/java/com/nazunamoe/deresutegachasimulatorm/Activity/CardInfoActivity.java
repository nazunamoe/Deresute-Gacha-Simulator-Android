package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;
import com.squareup.picasso.Picasso;

public class CardInfoActivity extends AppCompatActivity {
    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson;
    Card card;
    Toolbar toolbar;
    int resourceId;
    Resources resources;
    ImageView type;

    TextView cardCharaName;
    TextView cardCV;
    TextView cardCVFuri;
    TextView cardType;
    TextView cardRarity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info2);
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        prefsEditor = appSharedPrefs.edit();
        gson = new Gson();
        String json = appSharedPrefs.getString("SelectedCard","");
        card = gson.fromJson(json, new TypeToken<Card>(){}.getType());
        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardInfoActivity.super.onBackPressed();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitle(card.CardName);
        type = findViewById(R.id.cardImage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.White,null), PorterDuff.Mode.SRC_ATOP);

        resources = getResources();
        resourceId = resources.getIdentifier("card_"+card.No, "drawable", getPackageName());
        Picasso.with(getApplicationContext()).load(resourceId).into(type);

        cardCharaName = findViewById(R.id.CardInfoCharaName);
        cardCV = findViewById(R.id.CardInfoCVName);
        cardCVFuri = findViewById(R.id.CardInfoCVFuri);
        cardType = findViewById(R.id.CardInfoType);
        cardRarity = findViewById(R.id.CardInfoRarity);


        cardCharaName.setText(getResources().getString(R.string.cardName)+card.CharaName);
    }
}
