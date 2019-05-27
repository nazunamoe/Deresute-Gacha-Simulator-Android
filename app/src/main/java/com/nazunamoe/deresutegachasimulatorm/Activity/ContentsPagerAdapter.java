package com.nazunamoe.deresutegachasimulatorm.Activity;
import android.icu.text.IDNA;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nazunamoe.deresutegachasimulatorm.Fragments.MoneyFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.GachaFragment;
import com.nazunamoe.deresutegachasimulatorm.Fragments.InfoFragment;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount){
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                MoneyFragment moneyFragment = new MoneyFragment();
                return moneyFragment;
            case 1:
                GachaFragment gachaFragment = new GachaFragment();
                return gachaFragment;
            case 2:
                InfoFragment infoFragment = new InfoFragment();
                return infoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mPageCount;
    }
}
