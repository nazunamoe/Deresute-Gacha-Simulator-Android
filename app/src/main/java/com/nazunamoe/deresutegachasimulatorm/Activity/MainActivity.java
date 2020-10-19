package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuInflater;
import androidx.core.view.GravityCompat;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Database.DatabaseHelper;
import com.nazunamoe.deresutegachasimulatorm.Fragments.GachaFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.InfoFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.LimitedFragment;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity
        implements GachaFragment.OnFragmentInteractionListener {

    Toolbar toolbar;
    DatabaseHelper mDBHelper;
    SQLiteDatabase mDb;
    LinkedHashMap<Integer, Card> cardlist;
    SharedPreferences Shared;
    private static boolean firstRun = true;

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
            finishAffinity();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        res = getResources();

        setSupportActionBar(toolbar);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.maincontents,new GachaFragment());
        fragmentTransaction.commit();

        TabLayout tabLayout = findViewById(R.id.mainTab);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selected = null;
                switch(tab.getPosition()) {
                    case 0: {
                        selected = new GachaFragment();
                        break;
                    }
                    case 1: {
                        selected = new LimitedFragment();
                        break;
                    }
                    case 2: {
                        selected = new InfoFragment();
                        break;
                    }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontents, selected).commit();
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
            mDBHelper = new DatabaseHelper(this);

            try {
                mDb = mDBHelper.getWritableDatabase();
            } catch (SQLException mSQLException) {
                throw mSQLException;
            }
            mDBHelper.openDataBase();

            cardlist = mDBHelper.getAllCardMap();

            Shared = getSharedPreferences("Shared", MODE_PRIVATE);
            SharedPreferences.Editor editor = Shared.edit();

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
        super.onBackPressed();
    }

    public static Resources getResourses() {
        return res;
    }

}
