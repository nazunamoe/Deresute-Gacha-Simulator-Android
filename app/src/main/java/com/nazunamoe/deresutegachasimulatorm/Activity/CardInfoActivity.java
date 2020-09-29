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

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;

public class CardInfoActivity extends AppCompatActivity {
    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson;
    Card card;
    Card card_trained;

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

    private void initialize() {
        CardImage = findViewById(R.id.cardInfoCardImage);
        CardName = findViewById(R.id.cardInfoCardName);
        CardRarity = findViewById(R.id.cardInfoCardRarity);
        CardType = findViewById(R.id.cardInfoCardType);
        CardMinVocal = findViewById(R.id.cardInfoCardMinVocal);
        CardMinDance = findViewById(R.id.cardInfoCardMinDance);
        CardMinVisual = findViewById(R.id.cardInfoCardMinVisual);
        CardMinHp = findViewById(R.id.cardInfoCardMinHp);
        CardMinSum = findViewById(R.id.cardInfoCardMinSum);
        CardMaxVocal = findViewById(R.id.cardInfoCardMaxVocal);
        CardMaxDance = findViewById(R.id.cardInfoCardMaxDance);
        CardMaxVisual = findViewById(R.id.cardInfoCardMaxVisual);
        CardMaxHp = findViewById(R.id.cardInfoCardMaxHp);
        CardMaxSum = findViewById(R.id.cardInfoCardMaxSum);
        CardSkill = findViewById(R.id.cardInfoCardSkill);
        CardSkillStatus = findViewById(R.id.cardInfoCardSkillStatus);
        CardCenterSkill = findViewById(R.id.cardInfoCardCenterSkill);
        CardCenterSkillStatus = findViewById(R.id.cardInfoCardCenterSkillStatus);
        CardInfoTitle = findViewById(R.id.toolbar5_title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_info_2);

        toolbar = findViewById(R.id.toolbar5);

        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        prefsEditor = appSharedPrefs.edit();
        gson = new Gson();
        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardInfoActivity.super.onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initialize();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.cardinfotab) ;
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            String json;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0: {
                        json = appSharedPrefs.getString("SelectedCard","");
                        break;
                    }
                    case 1: {
                        json = appSharedPrefs.getString("SelectedCardTrained","");

                        break;
                    }
                }
                card = gson.fromJson(json, new TypeToken<Card>(){}.getType());
                updateData(card);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // do nothing
            }
        }) ;

        String json = appSharedPrefs.getString("SelectedCard","");
        card = gson.fromJson(json, new TypeToken<Card>(){}.getType());

        CardInfoTitle.setText(card.CardName);

        CardImage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://starlight.kirara.ca/card/"+card.No;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        updateData(card);
    }

    private void updateData(Card updatecard) {

        Glide.with(this)
                .load("https://hidamarirhodonite.kirara.ca/icon_card/"+updatecard.No+".png")
                .into(CardImage);
        CardName.setText(updatecard.CharaName);
        CardRarity.setText(updatecard.Rarity);
        CardType.setText(updatecard.Type);
        CardMinVocal.setText(""+updatecard.Vocal_Min);
        CardMinDance.setText(""+updatecard.Dance_Min);
        CardMinVisual.setText(""+updatecard.Visual_Min);
        CardMinHp.setText(""+updatecard.Hp_Min);
        CardMinSum.setText(String.valueOf(updatecard.Vocal_Min + updatecard.Visual_Min + updatecard.Dance_Min));
        CardMaxVocal.setText(""+updatecard.Vocal_Max);
        CardMaxDance.setText(""+updatecard.Dance_Max);
        CardMaxVisual.setText(""+updatecard.Visual_Max);
        CardMaxHp.setText(""+updatecard.Hp_Max);
        CardMaxSum.setText(String.valueOf(updatecard.Vocal_Max + updatecard.Visual_Max + updatecard.Dance_Max));
        CardSkill.setText(updatecard.SkillName);
        CardSkillStatus.setText(updatecard.SKillExplain);
        CardCenterSkill.setText(updatecard.CenterSkillName);
        CardCenterSkillStatus.setText(updatecard.CenterSkillExplain);

    }
}
