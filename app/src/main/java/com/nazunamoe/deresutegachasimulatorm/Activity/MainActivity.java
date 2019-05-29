package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Card.CustomListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Card.GachaCardData;
import com.nazunamoe.deresutegachasimulatorm.Database.DatabaseHelper;
import com.nazunamoe.deresutegachasimulatorm.Fragments.GachaFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.InfoFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.MoneyFragment;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GachaFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener, MoneyFragment.OnFragmentInteractionListener {

    public ArrayList<GachaCardData> test = new ArrayList<GachaCardData>();
    Toolbar toolbar;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);

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

        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout_tab);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.MoneyMenu));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.GachaMenu));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.InfoMenu));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.Viewpage);
        final PageAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void onFragmentInteraction(String input) {

    }

    @Override
    public void test(String input) {
    }
}
