package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nazunamoe.deresutegachasimulatorm.Class.Card;
import com.nazunamoe.deresutegachasimulatorm.Database.DatabaseHelper;
import com.nazunamoe.deresutegachasimulatorm.Fragments.GachaFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.InfoFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.LimitedFragment;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements GachaFragment.OnFragmentInteractionListener {

    Toolbar toolbar;
    DatabaseHelper mDBHelper;
    SQLiteDatabase mDb;
    ArrayList<Card> cardlist;
    SharedPreferences Shared;
    SharedPreferences.Editor editor;

    private static boolean firstRun = true;

    GachaFragment gachaFragment;
    InfoFragment infoFragment;
    LimitedFragment limitedFragment;

    private static Resources res;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_info) {
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
            alert_confirm.setMessage(getResources().getString(R.string.CurrentVersion)+" : "+getResources().getString(R.string.Version));
            alert_confirm.setPositiveButton("OK", null);
            AlertDialog alert = alert_confirm.create();

            alert.setTitle(R.string.info);
            alert.show();
        }
        if (menuItem.getItemId() == R.id.p_action) {
            Intent intent = new Intent(getApplicationContext(), pActivity.class);
            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.exit) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.exit_dialogue_title)
                    .setMessage(R.string.exit_dialogue)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Shared = getSharedPreferences("Shared", MODE_PRIVATE);
        editor = Shared.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        res = getResources();

        setSupportActionBar(toolbar);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        gachaFragment = new GachaFragment();
        limitedFragment = new LimitedFragment();
        infoFragment = new InfoFragment();

        TabLayout tabLayout = findViewById(R.id.mainTab);

        if(Shared.getString("SelectedTab", "").equals("Gacha")) {
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();
        }
        if(Shared.getString("SelectedTab", "").equals("Info"))  {
            TabLayout.Tab tab = tabLayout.getTabAt(1);
            tab.select();
        }
        if(Shared.getString("SelectedTab", "").equals("Limited"))  {
            TabLayout.Tab tab = tabLayout.getTabAt(2);
            tab.select();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selected = null;
                switch(tab.getPosition()) {
                    case 0: {
                        selected = gachaFragment;
                        editor.putString("SelectedTab","Gacha").apply();
                        break;
                    }
                    case 1: {
                        selected = infoFragment;
                        editor.putString("SelectedTab","Info").apply();
                        break;
                    }
                    case 2: {
                        selected = limitedFragment;
                        editor.putString("SelectedTab","Limited").apply();
                        break;
                    }
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.maincontents, selected)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if(firstRun)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.maincontents, gachaFragment)
                    .commit();

            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();

            mDBHelper = new DatabaseHelper(this);

            try {
                mDb = mDBHelper.getWritableDatabase();
            } catch (SQLException mSQLException) {
                throw mSQLException;
            }
            mDBHelper.openDataBase();

            cardlist = mDBHelper.getAllCardList();

            Gson gson = new Gson();

            String CardListJson = gson.toJson(cardlist);
            editor.putString("CardList",CardListJson).apply();

            editor.putFloat("SSRP",(float)3.0).apply();
            editor.putFloat("SRP",(float)12.0).apply();
        }
        firstRun = false;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.exit_dialogue_title)
                .setMessage(R.string.exit_dialogue)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    public static Resources getResourses() {
        return res;
    }

}
