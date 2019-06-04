package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.nazunamoe.deresutegachasimulatorm.Fragments.GachaFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.InfoFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.MoneyFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    int NumOfTabs;

    public PageAdapter(FragmentManager fm, int NumofTabs){
        super(fm);
        this.NumOfTabs = NumofTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position) {
            case 0:
                MoneyFragment tab1 = new MoneyFragment();
                return tab1;
            case 1:
                GachaFragment tab2 = new GachaFragment();
                return tab2;
            case 2:
                InfoFragment tab3 = new InfoFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}
