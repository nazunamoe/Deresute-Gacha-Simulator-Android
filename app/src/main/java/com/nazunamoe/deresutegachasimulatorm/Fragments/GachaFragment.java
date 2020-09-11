package com.nazunamoe.deresutegachasimulatorm.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import com.nazunamoe.deresutegachasimulatorm.Card.CustomListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Gacha.Gacha;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class GachaFragment extends Fragment {
    ArrayList<Card> wholelist = new ArrayList<Card>();
    //ArrayList<Card> usinglist = new ArrayList<Card>(); // 나중에 카드 보관함 기능 추가하면 그때 사용 예정

    private OnFragmentInteractionListener mListener;
    CustomListAdapter adapter;

    int SSRare = 0;
    int SRare = 0;
    int Rare = 0;

    int Cute = 0;
    int Cool = 0;
    int Passion = 0;

    Gacha gacha;
    Resources resources;
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
        String json = appSharedPrefs.getString("GachaCardList","");
        wholelist = gson.fromJson(json, new TypeToken<ArrayList<Card>>(){}.getType());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gacha,container,false);
        final SharedPreferences pref = getActivity().getSharedPreferences("Shared", MODE_PRIVATE);
        gacha = new Gacha();

        Button onegacha = (Button) view.findViewById(R.id.gacha10);
        Button tengacha = (Button) view.findViewById(R.id.gacha1);
        Button goldgacha = (Button) view.findViewById(R.id.gacha1_gold);

        adapter = new CustomListAdapter();
        ListView listView = (ListView)view.findViewById(R.id.gachacardlist);
        listView.setAdapter(adapter);


        SSRareNumber = (TextView)view.findViewById(R.id.SSRareNum);
        SRareNumber = (TextView)view.findViewById(R.id.SRareNum);
        RareNumber = (TextView)view.findViewById(R.id.RareNum);

        CuteNumber = (TextView)view.findViewById(R.id.CuteNum);
        CoolNumber = (TextView)view.findViewById(R.id.CoolNum);
        PassionNumber = (TextView)view.findViewById(R.id.PassionNum);

        String json = appSharedPrefs.getString("GachaCardList","");
        wholelist = gson.fromJson(json, new TypeToken<ArrayList<Card>>(){}.getType());

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

        goldgacha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Gacha_Execute(pref, false);
            }
        });
        return view;
    }

    private void Gacha_Execute(SharedPreferences pref, Boolean ten) {
        Card gacharesult = null;
        adapter.clearItem();
        if(ten) {
            for(int a=0; a<9; a++){
                gacharesult = getRarityCard(gacha.GachaExecute(pref.getFloat("SSRP",(float)3.0),pref.getFloat("SRP",(float)12.0)));
                //usinglist.add(gacharesult);
                adapter.addItem(gacharesult);
            }
            gacharesult = getRarityCard(gacha.rensyaSR(pref.getFloat("SSRP",(float)3.0)));
        } else {
            gacharesult = getRarityCard(gacha.GachaExecute(pref.getFloat("SSRP",(float)3.0),pref.getFloat("SRP",(float)12.0)));
        }
       //usinglist.add(gacharesult);
        adapter.addItem(gacharesult);
        updateGachaStatus();
        adapter.notifyDataSetChanged();
    }

    private Card cardRarityTypeCount(Card card){
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
        return card;
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

    private void updateGachaStatus(){
        SSRareNumber.setText(""+SSRare);
        SRareNumber.setText(""+SRare);
        RareNumber.setText(""+Rare);

        CuteNumber.setText(""+Cute);
        CoolNumber.setText(""+Cool);
        PassionNumber.setText(""+Passion);

        SSRare = 0;
        SRare = 0;
        Rare = 0;
        Cute = 0;
        Cool = 0;
        Passion = 0;
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