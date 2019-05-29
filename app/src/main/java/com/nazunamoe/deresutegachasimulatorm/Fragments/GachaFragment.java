package com.nazunamoe.deresutegachasimulatorm.Fragments;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.Card.CustomListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Database.DatabaseHelper;
import com.nazunamoe.deresutegachasimulatorm.Gacha.Gacha;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GachaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GachaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GachaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    CustomListAdapter adapter;
    ListView listView;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    Gacha gacha;

    public GachaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GachaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GachaFragment newInstance(String param1, String param2) {
        GachaFragment fragment = new GachaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mDBHelper = new DatabaseHelper(getActivity());
        try{
            mDBHelper.updateDataBase();
        }catch(IOException e){

        }
        super.onCreate(savedInstanceState);
    }

    public void addCard(String name, String rarity, String type){
        adapter.addItem(name,rarity,type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gacha = new Gacha(0);



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


        View view = inflater.inflate(R.layout.fragment_gacha,container,false);
        Button onegacha = (Button) view.findViewById(R.id.gacha1);
        Button tengacha = (Button) view.findViewById(R.id.gacha10);
        Button goldgacha = (Button) view.findViewById(R.id.gacha1_gold);
        adapter = new CustomListAdapter();
        ListView listView = (ListView)view.findViewById(R.id.gachacardlist);
        listView.setAdapter(adapter);



        onegacha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Card gacharesult = null;
                adapter.clearItem();
                while(true){
                    gacharesult = mDBHelper.getRarityCard(gacha.GachaExecute());
                    if(gacharesult.Limited == false && gacharesult.EventName == null){
                        break;
                    }
                }
                adapter.addItem(gacharesult.CardName,gacharesult.Rarity,gacharesult.Type);
                adapter.notifyDataSetChanged();
            }
        });

        tengacha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                adapter.clearItem();
                Card gacharesult = null;
                for(int a=0; a<9; a++){
                    while(true){
                        gacharesult = mDBHelper.getRarityCard(gacha.GachaExecute());
                        if(gacharesult.Limited == false && gacharesult.EventName == null){
                            break;
                        }
                    }
                    adapter.addItem(gacharesult.CardName,gacharesult.Rarity,gacharesult.Type);
                }
                while(true){
                    gacharesult = mDBHelper.getRarityCard(gacha.rensyaSR());
                    if(gacharesult.Limited == false && gacharesult.EventName == null){
                        break;
                    }
                }
                adapter.addItem(gacharesult.CardName,gacharesult.Rarity,gacharesult.Type);
                adapter.notifyDataSetChanged();
            }
        });

        goldgacha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Card gacharesult = null;
                adapter.clearItem();
                while(true){
                    gacharesult = mDBHelper.getRarityCard(gacha.GachaExecute());
                    if(gacharesult.Limited == false && gacharesult.EventName == null){
                        break;
                    }
                }
                adapter.addItem(gacharesult.CardName,gacharesult.Rarity,gacharesult.Type);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void test(String input);
    }


}
