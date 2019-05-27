package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nazunamoe.deresutegachasimulatorm.R;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.MoneyMenu));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.GachaMenu));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.InfoMenu));
    }
}
