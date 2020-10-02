package com.nazunamoe.deresutegachasimulatorm.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Adapter.GachaListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Gacha.Gacha;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class GachaFragment extends Fragment {

    LinkedHashMap<Integer, Card> Whole_CardList = new LinkedHashMap<>();
    LinkedHashMap<Integer, Card> Gacha_CardList = new LinkedHashMap<>();
    Set<Map.Entry<Integer, Card>> Gacha_CardList_MapSet;

    private OnFragmentInteractionListener mListener;
    GachaListAdapter adapter;

    int SSRare = 0;
    int SRare = 0;
    int Rare = 0;

    int Cute = 0;
    int Cool = 0;
    int Passion = 0;

    Gacha gacha;
    TextView SSRareNumber;
    TextView SRareNumber;
    TextView RareNumber;
    TextView CuteNumber;
    TextView CoolNumber;
    TextView PassionNumber;
    View view;

    CardView CardInfoView;

    Switch Max_Stat;
    Switch Training;

    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson;

    String json;
    Type type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefsEditor = appSharedPrefs.edit();
        gson = new Gson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gacha,container,false);
        final SharedPreferences pref = getActivity().getSharedPreferences("Shared", MODE_PRIVATE);
        gacha = new Gacha();

        Button onegacha = view.findViewById(R.id.gacha1);
        Button tengacha = view.findViewById(R.id.gacha10);
        Button resetbutton = view.findViewById(R.id.resetbutton);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        CardInfoView = view.findViewById(R.id.gacharesultcardinfo);
        Max_Stat = view.findViewById(R.id.Max_Stat);
        Training = view.findViewById(R.id.Training);

        SSRareNumber = view.findViewById(R.id.SSRareNum);
        SRareNumber = view.findViewById(R.id.SRareNum);
        RareNumber = view.findViewById(R.id.RareNum);

        CuteNumber = view.findViewById(R.id.CuteNum);
        CoolNumber = view.findViewById(R.id.CoolNum);
        PassionNumber = view.findViewById(R.id.PassionNum);

        json = appSharedPrefs.getString("TempCardList","");
        type = new TypeToken<LinkedHashMap<Integer, Card>>(){}.getType();
        Whole_CardList = gson.fromJson(json, type);
        json = appSharedPrefs.getString("Gacha_CardList","");
        Gacha_CardList = gson.fromJson(json, type);

        if(Gacha_CardList != null) {
            Gacha_CardList_MapSet = Gacha_CardList.entrySet();
            adapter = new GachaListAdapter(Whole_CardList, new ArrayList<>(Gacha_CardList.keySet()), width, CardInfoView, Max_Stat.isChecked(), Training.isChecked());
            for(Map.Entry<Integer, Card> e : Gacha_CardList_MapSet) {
                cardRarityTypeCount(e.getValue());
                UpdateGachaStatus(false);
            }
        } else {
            Gacha_CardList = new LinkedHashMap<>();
            Gacha_CardList_MapSet = Gacha_CardList.entrySet();
            adapter = new GachaListAdapter(Whole_CardList, new ArrayList<>(Gacha_CardList.keySet()), width, CardInfoView, Max_Stat.isChecked(), Training.isChecked());
        }

        RecyclerView recyclerView = view.findViewById(R.id.gachacardlist);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 5, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        onegacha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Gacha_Execute(pref, false);
            }
        });

        tengacha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Gacha_Execute(pref, true);
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Gacha_CardList != null) {
                    Gacha_CardList.clear();
                    adapter.clearItem();
                    adapter.notifyDataSetChanged();
                    UpdateGachaStatus(true);
                    json = gson.toJson(Gacha_CardList);
                    prefsEditor.putString("Gacha_CardList", json);
                    prefsEditor.commit();
                }
            }
        });

        return view;
    }

    private void Gacha_Execute(SharedPreferences pref, Boolean ten) {
        Card gacharesult;
        if(Gacha_CardList != null) {
            Gacha_CardList.clear();
            UpdateGachaStatus(true);
        }
        else Gacha_CardList = new LinkedHashMap<>();
        adapter.clearItem();
        if(ten) {
            for(int a=0; a<9; a++){
                gacharesult = getRarityCard(gacha.GachaExecute(pref.getFloat("SSRP",(float)3.0),pref.getFloat("SRP",(float)12.0),false));
                Gacha_CardList.put(gacharesult.No, gacharesult);
                adapter.addItem(gacharesult);
                cardRarityTypeCount(gacharesult);
                adapter.notifyDataSetChanged();
            }
            gacharesult = getRarityCard(gacha.GachaExecute(pref.getFloat("SSRP",(float)3.0),pref.getFloat("SRP",(float)12.0),true));
        } else {
            gacharesult = getRarityCard(gacha.GachaExecute(pref.getFloat("SSRP",(float)3.0),pref.getFloat("SRP",(float)12.0),false));
        }
        Gacha_CardList.put(gacharesult.No, gacharesult);
        adapter.addItem(gacharesult);
        cardRarityTypeCount(gacharesult);
        UpdateGachaStatus(true);
        adapter.notifyDataSetChanged();
        json = gson.toJson(Gacha_CardList);
        prefsEditor.putString("Gacha_CardList", json);
        prefsEditor.commit();
    }

    private void cardRarityTypeCount(Card card){

        switch(card.Rarity){
            case "SS RARE":{
                SSRare++;
                break;
            }
            case "S RARE":{
                SRare++;
                break;
            }
            case "RARE":{
                Rare++;
                break;
            }
        }

        switch(card.Type){
            case "CUTE":{
                Cute++;
                break;
            }
            case "COOL":{
                Cool++;
                break;
            }
            case "PASSION":{
                Passion++;
                break;
            }
        }
    }

    private Card getRarityCard(int Rarity){
        Random random = new Random();
        Set<Map.Entry<Integer, Card>> mapSet = Whole_CardList.entrySet();
        Map.Entry<Integer, Card> elementAt;
        int pos;
        while(true) {
            pos = random.nextInt(Whole_CardList.size());
            elementAt = (Map.Entry<Integer, Card>) mapSet.toArray()[pos];
            if((elementAt.getValue().RarityInt == Rarity) && elementAt.getValue().Availablity && !elementAt.getValue().EventCard){
                return elementAt.getValue();
            }
        }
    }

    private void InitializeGachaStatus(){
        SSRare = 0;
        SRare = 0;
        Rare = 0;
        Cute = 0;
        Cool = 0;
        Passion = 0;
    }

    private void UpdateGachaStatus(Boolean initial) {
        SSRareNumber.setText(""+SSRare);
        SRareNumber.setText(""+SRare);
        RareNumber.setText(""+Rare);

        CuteNumber.setText(""+Cute);
        CoolNumber.setText(""+Cool);
        PassionNumber.setText(""+Passion);

        if(initial) InitializeGachaStatus();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
    }
}