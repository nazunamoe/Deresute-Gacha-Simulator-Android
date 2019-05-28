package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nazunamoe.deresutegachasimulatorm.Card.GachaCardData;
import com.nazunamoe.deresutegachasimulatorm.Fragments.GachaFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.InfoFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.MoneyFragment;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GachaFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener, MoneyFragment.OnFragmentInteractionListener {

    public ArrayList<GachaCardData> test = new ArrayList<GachaCardData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
