package com.nazunamoe.deresutegachasimulatorm.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class LimitedFragment extends Fragment {

    public LimitedFragment() {
        // Required empty public constructor
    }

    public static LimitedFragment newInstance(String param1, String param2) {
        LimitedFragment fragment = new LimitedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}