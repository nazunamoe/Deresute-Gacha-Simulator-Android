package com.nazunamoe.deresutegachasimulatorm.Fragments;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nazunamoe.deresutegachasimulatorm.Adapter.GachaSeasonListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Class.Gacha_Season;
import com.nazunamoe.deresutegachasimulatorm.Database.DatabaseHelper;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;


public class LimitedFragment extends Fragment {

    View view;

    RecyclerView recyclerView;

    GachaSeasonListAdapter gachaSeasonListAdapter;

    ArrayList<Gacha_Season> seasonlist;

    DatabaseHelper mDBHelper;
    SQLiteDatabase mDb;

    CheckBox Fes_Switch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void getDB() {
        mDBHelper = new DatabaseHelper(getActivity());

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        mDBHelper.openDataBase();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_limited,container,false);

        recyclerView = view.findViewById(R.id.gacha_season_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false));

        getDB();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Fes_Switch = view.findViewById(R.id.FesCardSwitch);

        Fes_Switch.setChecked(mDBHelper.getFesLimited() != 1);

        Fes_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) mDBHelper.setFesLimited(0);
                else mDBHelper.setFesLimited(1);
            }
        });

        if(seasonlist == null) seasonlist = mDBHelper.getAllSeasons();

        gachaSeasonListAdapter = new GachaSeasonListAdapter(seasonlist, getContext(), width, mDBHelper);
        recyclerView.setAdapter(gachaSeasonListAdapter);

        return view;
    }

    }