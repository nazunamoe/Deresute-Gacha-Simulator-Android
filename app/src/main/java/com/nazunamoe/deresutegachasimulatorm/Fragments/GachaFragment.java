package com.nazunamoe.deresutegachasimulatorm.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Adapter.CustomListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Gacha.Gacha;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class GachaFragment extends Fragment {
    ArrayList<Card> wholelist = new ArrayList<>();
    ArrayList<Card> usinglist = new ArrayList<>();

    private OnFragmentInteractionListener mListener;
    CustomListAdapter adapter;

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

    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson;

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

        Button onegacha = (Button) view.findViewById(R.id.gacha1);
        Button tengacha = (Button) view.findViewById(R.id.gacha10);
        Button resetbutton = (Button) view.findViewById(R.id.resetbutton);

        adapter = new CustomListAdapter();
        ListView listView = (ListView)view.findViewById(R.id.gachacardlist);
        listView.setAdapter(adapter);

        SSRareNumber = (TextView)view.findViewById(R.id.SSRareNum);
        SRareNumber = (TextView)view.findViewById(R.id.SRareNum);
        RareNumber = (TextView)view.findViewById(R.id.RareNum);

        CuteNumber = (TextView)view.findViewById(R.id.CuteNum);
        CoolNumber = (TextView)view.findViewById(R.id.CoolNum);
        PassionNumber = (TextView)view.findViewById(R.id.PassionNum);

        String json = appSharedPrefs.getString("CardList","");
        Type type = new TypeToken<ArrayList<Card>>(){}.getType();

        wholelist = gson.fromJson(json, type);
        json = appSharedPrefs.getString("GachaList","");

        usinglist = gson.fromJson(json, type);


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
                if(usinglist != null) {
                    usinglist.clear();
                    adapter.clearItem();
                    adapter.notifyDataSetChanged();

                    UpdateGachaStatus(true);

                    String json = gson.toJson(usinglist);
                    prefsEditor.putString("GachaList", json);
                    prefsEditor.commit();
                }
            }
        });

        if(usinglist != null) {
            for(Card e : usinglist) {
                cardRarityTypeCount(e);
                adapter.addItem(e);
                UpdateGachaStatus(false);
            }
        }

        return view;
    }

    private void Gacha_Execute(SharedPreferences pref, Boolean ten) {
        Card gacharesult;

        if(usinglist != null) {
            usinglist.clear();
            UpdateGachaStatus(true);
        }
        else usinglist = new ArrayList<>();

        adapter.clearItem();
        if(ten) {
            for(int a=0; a<9; a++){
                gacharesult = getRarityCard(gacha.GachaExecute(pref.getFloat("SSRP",(float)3.0),pref.getFloat("SRP",(float)12.0),false));
                usinglist.add(gacharesult);
                adapter.addItem(gacharesult);
            }
            gacharesult = getRarityCard(gacha.GachaExecute(pref.getFloat("SSRP",(float)3.0),pref.getFloat("SRP",(float)12.0),true));
        } else {
            gacharesult = getRarityCard(gacha.GachaExecute(pref.getFloat("SSRP",(float)3.0),pref.getFloat("SRP",(float)12.0),false));
        }
        usinglist.add(gacharesult);
        adapter.addItem(gacharesult);
        UpdateGachaStatus(true);
        adapter.notifyDataSetChanged();
        String json = gson.toJson(usinglist);
        prefsEditor.putString("GachaList", json);
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
        ArrayList<Card> tempList = new ArrayList<Card>();
        for(int a=0; a<wholelist.size(); a++){
            if((wholelist.get(a).RarityInt == Rarity) && wholelist.get(a).Availablity && !wholelist.get(a).EventCard){
                tempList.add(wholelist.get(a));
            }
        }
        Random random = new Random();
        int pos = random.nextInt(tempList.size());
        cardRarityTypeCount(tempList.get(pos));
        return tempList.get(pos);
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