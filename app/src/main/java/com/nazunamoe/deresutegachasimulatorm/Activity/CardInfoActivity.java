package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class CardInfoActivity extends AppCompatActivity {
    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson;
    Card card;
    Toolbar toolbar;

    ImageButton CardImage;

    TextView CardInfoTitle;

    TextView CardName;
    TextView CardRarity;
    TextView CardType;

    TextView CardMinVocal;
    TextView CardMinDance;
    TextView CardMinVisual;
    TextView CardMinHp;
    TextView CardMinSum;

    TextView CardMaxVocal;
    TextView CardMaxDance;
    TextView CardMaxVisual;
    TextView CardMaxHp;
    TextView CardMaxSum;

    TextView CardSkill;
    TextView CardSkillStatus;

    TextView CardCenterSkill;
    TextView CardCenterSkillStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_info2);

        toolbar = findViewById(R.id.toolbar5);

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

        CardInfoTitle = findViewById(R.id.toolbar5_title);
        CardInfoTitle.setText(card.CardName);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CardImage = (ImageButton)findViewById(R.id.cardInfoCardImage);

        CardName = (TextView)findViewById(R.id.cardInfoCardName);
        CardRarity = (TextView)findViewById(R.id.cardInfoCardRarity);
        CardType = (TextView)findViewById(R.id.cardInfoCardType);

        CardMinVocal = (TextView)findViewById(R.id.cardInfoCardMinVocal);
        CardMinDance = (TextView)findViewById(R.id.cardInfoCardMinDance);
        CardMinVisual = (TextView)findViewById(R.id.cardInfoCardMinVisual);
        CardMinHp = (TextView)findViewById(R.id.cardInfoCardMinHp);
        CardMinSum = (TextView)findViewById(R.id.cardInfoCardMinSum);

        CardMaxVocal = (TextView)findViewById(R.id.cardInfoCardMaxVocal);
        CardMaxDance = (TextView)findViewById(R.id.cardInfoCardMaxDance);
        CardMaxVisual = (TextView)findViewById(R.id.cardInfoCardMaxVisual);
        CardMaxHp = (TextView)findViewById(R.id.cardInfoCardMaxHp);
        CardMaxSum = (TextView)findViewById(R.id.cardInfoCardMaxSum);

        CardSkill = (TextView)findViewById(R.id.cardInfoCardSkill);
        CardSkillStatus = (TextView)findViewById(R.id.cardInfoCardSkillStatus);

        CardCenterSkill = (TextView)findViewById(R.id.cardInfoCardCenterSkill);
        CardCenterSkillStatus = (TextView)findViewById(R.id.cardInfoCardCenterSkillStatus);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.get().load("https://hidamarirhodonite.kirara.ca/icon_card/"+card.No+".png").into(CardImage);
        built.get().setLoggingEnabled(false);

        CardName.setText(card.CharaName);
        CardRarity.setText(card.Rarity);
        CardType.setText(card.Type);

        CardMinVocal.setText(""+card.Vocal_Min);
        CardMinDance.setText(""+card.Dance_Min);
        CardMinVisual.setText(""+card.Visual_Min);
        CardMinHp.setText(""+card.Hp_Min);
        int sum_min = card.Vocal_Min + card.Visual_Min + card.Dance_Min;
        CardMinSum.setText(""+sum_min);

        CardMaxVocal.setText(""+card.Vocal_Max);
        CardMaxDance.setText(""+card.Dance_Max);
        CardMaxVisual.setText(""+card.Visual_Max);
        CardMaxHp.setText(""+card.Hp_Max);
        int sum_max = card.Vocal_Max + card.Visual_Max + card.Dance_Max;
        CardMaxSum.setText(""+sum_max);

        CardSkill.setText(card.SkillName);
        CardSkillStatus.setText(card.SKillExplain);

        CardCenterSkill.setText(card.CenterSkillName);
        CardCenterSkillStatus.setText(card.CenterSkillExplain);

        CardImage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://starlight.kirara.ca/card/"+card.No;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
