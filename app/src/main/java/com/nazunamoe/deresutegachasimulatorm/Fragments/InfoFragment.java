package com.nazunamoe.deresutegachasimulatorm.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazunamoe.deresutegachasimulatorm.Adapter.InfoListAdapter;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class InfoFragment extends Fragment {

    InfoListAdapter adapter;
    View view;

    ArrayList<Card> card_list;

    CheckBox cuteonly_check;
    CheckBox coolonly_check;
    CheckBox passiononly_check;

    CheckBox ssronly_check;
    CheckBox sronly_check;
    CheckBox ronly_check;
    CheckBox nonly_check;

    CheckBox usualonly_check;
    CheckBox limitedonly_check;
    CheckBox fesonly_check;
    CheckBox eventonly_check;

    CheckBox skill_scorebonus_check;
    CheckBox skill_combobonus_check;
    CheckBox skill_combosupport_check;
    CheckBox skill_liferecovery_check;
    CheckBox skill_damageguard_check;
    CheckBox skill_overload_check;
    CheckBox skill_concentration_check;
    CheckBox skill_skillboost_check;
    CheckBox skill_focus_check;
    CheckBox skill_allrounder_check;
    CheckBox skill_encore_check;
    CheckBox skill_lifesparkle_check;
    CheckBox skill_tricolorsynergy_check;
    CheckBox skill_coordinate_check;
    CheckBox skill_tuning_check;
    CheckBox skill_flickact_check;
    CheckBox skill_longact_check;
    CheckBox skill_slideact_check;
    CheckBox skill_ensemble_check;
    CheckBox skill_vocalmotif_check;
    CheckBox skill_dancemotif_check;
    CheckBox skill_visualmotif_check;
    CheckBox skill_tricolorsymphony_check;
    CheckBox skill_alternate_check;

    CheckBox centerskill_voice;
    CheckBox centerskill_step;
    CheckBox centerskill_make;
    CheckBox centerskill_brilliance;
    CheckBox centerskill_energy;
    CheckBox centerskill_ability;
    CheckBox centerskill_tricolorvoice;
    CheckBox centerskill_tricolorstep;
    CheckBox centerskill_tricolormake;
    CheckBox centerskill_tricolorability;
    CheckBox centerskill_princess;
    CheckBox centerskill_cheer;
    CheckBox centerskill_fortunepresent;
    CheckBox centerskill_cinderellacharm;
    CheckBox centerskill_cutecool;
    CheckBox centerskill_cutepassion;
    CheckBox centerskill_coolpassion;
    CheckBox centerskill_unison;
    CheckBox centerskill_resonantvoice;
    CheckBox centerskill_resonantstep;
    CheckBox centerskill_resonantmake;
    CheckBox centerskill_cinderellayell;
    CheckBox centerskill_worldlevel;

    LinearLayout settings;
    RecyclerView recyclerView;
    CardView listViewCard;

    SharedPreferences appSharedPrefs;
    Gson gson;
    String json;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info,container,false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        appSharedPrefs = getActivity().getSharedPreferences("Shared", MODE_PRIVATE);
        gson = new Gson();
        json = appSharedPrefs.getString("CardList","");
        card_list = gson.fromJson(json, new TypeToken<ArrayList<Card>>(){}.getType());

        adapter = new InfoListAdapter(card_list, width);
        recyclerView = view.findViewById(R.id.CardList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false));
        listViewCard = view.findViewById(R.id.cardlistcard);
        settings = view.findViewById(R.id.settings);

        recyclerView.setAdapter(adapter);

        cuteonly_check = view.findViewById(R.id.CuteOnly);
        coolonly_check = view.findViewById(R.id.CoolOnly);
        passiononly_check = view.findViewById(R.id.PassionOnly);

        ssronly_check = view.findViewById(R.id.SSROnly);
        sronly_check = view.findViewById(R.id.SROnly);
        ronly_check = view.findViewById(R.id.ROnly);
        nonly_check = view.findViewById(R.id.NormalOnly);

        usualonly_check = view.findViewById(R.id.UsualOnly);
        limitedonly_check = view.findViewById(R.id.LimitedOnly);
        fesonly_check = view.findViewById(R.id.FesOnly);
        eventonly_check = view.findViewById(R.id.EventOnly);

        skill_scorebonus_check = view.findViewById(R.id.Skill_ScoreBonus);
        skill_combobonus_check = view.findViewById(R.id.Skill_ComboBonus);
        skill_combosupport_check = view.findViewById(R.id.Skill_ComboSupport);
        skill_concentration_check = view.findViewById(R.id.Skill_Concentration);
        skill_skillboost_check = view.findViewById(R.id.Skill_SkillBoost);
        skill_focus_check = view.findViewById(R.id.Skill_Focus);
        skill_allrounder_check = view.findViewById(R.id.Skill_AllRounder);
        skill_encore_check = view.findViewById(R.id.Skill_Encore);
        skill_liferecovery_check = view.findViewById(R.id.Skill_LifeRecovery);
        skill_damageguard_check = view.findViewById(R.id.Skill_DamageGuard);
        skill_overload_check = view.findViewById(R.id.Skill_Overload);
        skill_lifesparkle_check = view.findViewById(R.id.Skill_LifeSparkle);
        skill_tricolorsynergy_check = view.findViewById(R.id.Skill_TricolorSynergy);
        skill_coordinate_check = view.findViewById(R.id.Skill_Coordinate);
        skill_tuning_check = view.findViewById(R.id.Skill_Tuning);
        skill_flickact_check = view.findViewById(R.id.Skill_FlickAct);
        skill_longact_check = view.findViewById(R.id.Skill_LongAct);
        skill_slideact_check = view.findViewById(R.id.Skill_SlideAct);
        skill_ensemble_check = view.findViewById(R.id.Skill_Ensemble);
        skill_vocalmotif_check = view.findViewById(R.id.Skill_VocalMotif);
        skill_dancemotif_check = view.findViewById(R.id.Skill_DanceMotif);
        skill_visualmotif_check = view.findViewById(R.id.Skill_VisualMotif);
        skill_tricolorsymphony_check = view.findViewById(R.id.Skill_TricolorSymphony);
        skill_alternate_check = view.findViewById(R.id.Skill_Alternate);

        centerskill_voice = view.findViewById(R.id.CenterSkill_Voice);
        centerskill_step = view.findViewById(R.id.CenterSkill_Step);
        centerskill_make = view.findViewById(R.id.CenterSkill_Make);
        centerskill_brilliance = view.findViewById(R.id.CenterSkill_Brilliance);
        centerskill_energy = view.findViewById(R.id.CenterSkill_LifeUpNormal);
        centerskill_ability = view.findViewById(R.id.CenterSkill_Ability);
        centerskill_tricolorvoice = view.findViewById(R.id.CenterSkill_TricolorVoice);
        centerskill_tricolorstep = view.findViewById(R.id.CenterSkill_TricolorStep);
        centerskill_tricolormake = view.findViewById(R.id.CenterSkill_TricolorMake);
        centerskill_tricolorability = view.findViewById(R.id.CenterSkill_TricolorAbility);
        centerskill_princess = view.findViewById(R.id.CenterSkill_Princess);
        centerskill_cheer = view.findViewById(R.id.CenterSkill_LifeUpCheer);
        centerskill_fortunepresent = view.findViewById(R.id.CenterSkill_FortunePresent);
        centerskill_cinderellacharm = view.findViewById(R.id.CenterSkill_CinderellaCharm);
        centerskill_cutecool = view.findViewById(R.id.CenterSkill_CuteCool);
        centerskill_cutepassion = view.findViewById(R.id.CenterSkill_CutePassion);
        centerskill_coolpassion = view.findViewById(R.id.CenterSkill_CoolPassion);
        centerskill_unison = view.findViewById(R.id.CenterSkill_Unison);
        centerskill_resonantvoice = view.findViewById(R.id.CenterSkill_ResoantVoice);
        centerskill_resonantstep = view.findViewById(R.id.CenterSkill_ResonantStep);
        centerskill_resonantmake = view.findViewById(R.id.CenterSkill_ResoantMake);
        centerskill_cinderellayell = view.findViewById(R.id.CenterSkill_CinderellaYell);
        centerskill_worldlevel = view.findViewById(R.id.CenterSkill_WorldLevel);

        // 여기서부터 속성 체크박스

        cuteonly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        coolonly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        passiononly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        // 여기서부터 레어 등급 체크박스

        ssronly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        sronly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        ronly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        nonly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        // 여기서부터 한정/이벤트 체크박스

        usualonly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        limitedonly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        fesonly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        eventonly_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_scorebonus_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        // 여기부터 스킬 체크박스

        skill_combobonus_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_combosupport_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_liferecovery_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_damageguard_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_overload_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_concentration_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_skillboost_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_focus_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_allrounder_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_encore_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_lifesparkle_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_tricolorsynergy_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_coordinate_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_tuning_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_flickact_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_longact_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_slideact_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_ensemble_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_vocalmotif_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_dancemotif_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_visualmotif_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_tricolorsymphony_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        skill_alternate_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        // 여기부터 센터스킬 체크박스

        centerskill_voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_step.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_make.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_brilliance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_energy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_ability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_tricolorvoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_tricolorstep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_tricolormake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_tricolorability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_princess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_cheer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_fortunepresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_cinderellacharm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_cutecool.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_cutepassion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_coolpassion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_unison.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_resonantvoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_resonantstep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_resonantmake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_cinderellayell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });

        centerskill_worldlevel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListbyType();
            }
        });
        System.out.println("list_size"+ card_list.size());
        updateListbyType();

        return view;
    }

    private void updateListbyType(){
        card_list = gson.fromJson(json, new TypeToken<ArrayList<Card>>(){}.getType());
        adapter.clearItem();
        for(int i = 0; i< card_list.size(); i++){
            if(card_list.get(i).No % 2 == 1) {
                if(cuteonly_check.isChecked() && (card_list.get(i)).No / 100000 == 1){
                    if(updateListbyRarity(card_list.get(i))) adapter.addItem((card_list.get(i)));
                }
                if(coolonly_check.isChecked() && (card_list.get(i)).No / 100000 == 2){
                    if(updateListbyRarity(card_list.get(i))) adapter.addItem((card_list.get(i)));
                }
                if(passiononly_check.isChecked() && (card_list.get(i)).No / 100000 == 3){
                    if(updateListbyRarity(card_list.get(i))) adapter.addItem((card_list.get(i)));
                }
                if(!cuteonly_check.isChecked() && !coolonly_check.isChecked() && !passiononly_check.isChecked()){
                    if(updateListbyRarity(card_list.get(i))) adapter.addItem((card_list.get(i)));
                }
            }

        }
        adapter.notifyDataSetChanged();
    }

    private boolean updateListbyRarity(Card input){
        if(ssronly_check.isChecked() && input.Rarity.equals("SS RARE")) return updateListbyLimited(input);
        if(sronly_check.isChecked() && input.Rarity.equals("S RARE")) return updateListbyLimited(input);
        if(ronly_check.isChecked() && input.Rarity.equals("RARE")) return updateListbyLimited(input);
        if(nonly_check.isChecked() && input.Rarity.equals("NORMAL")) return updateListbyLimited(input);
        if(!ssronly_check.isChecked() && !sronly_check.isChecked() && !ronly_check.isChecked() && !nonly_check.isChecked()) return updateListbyLimited(input);
        return false;
    }

    private boolean updateListbyLimited(Card input){
        if(usualonly_check.isChecked() && input.CardCategory == 0) return updateListBySkill(input);
        if(eventonly_check.isChecked() && input.CardCategory == 1) return updateListBySkill(input);
        if(limitedonly_check.isChecked() && input.CardCategory == 2) return updateListBySkill(input);
        if(fesonly_check.isChecked() && input.CardCategory == 3) return updateListBySkill(input);
        if(!usualonly_check.isChecked() && !limitedonly_check.isChecked() && !fesonly_check.isChecked() && !eventonly_check.isChecked()) return updateListBySkill(input);
        return false;
    }

    private boolean updateListBySkill(Card input) {
        if(skill_scorebonus_check.isChecked() && (input.SkillCode == 1 || input.SkillCode == 2)) return updateListByLeaderSkill(input);
        if(skill_combobonus_check.isChecked() && input.SkillCode == 4) return updateListByLeaderSkill(input);
        if(skill_combosupport_check.isChecked() && (input.SkillCode >= 5 && input.SkillCode <= 8)) return updateListByLeaderSkill(input);
        if(skill_liferecovery_check.isChecked() && input.SkillCode == 17) return updateListByLeaderSkill(input);
        if(skill_damageguard_check.isChecked() && input.SkillCode == 12) return updateListByLeaderSkill(input);
        if(skill_overload_check.isChecked() && input.SkillCode == 14) return updateListByLeaderSkill(input);
        if(skill_concentration_check.isChecked() && input.SkillCode == 15) return updateListByLeaderSkill(input);
        if(skill_skillboost_check.isChecked() && input.SkillCode == 20) return updateListByLeaderSkill(input);
        if(skill_focus_check.isChecked() && (input.SkillCode >= 21 && input.SkillCode <= 23)) return updateListByLeaderSkill(input);
        if(skill_allrounder_check.isChecked() && input.SkillCode == 24) return updateListByLeaderSkill(input);
        if(skill_encore_check.isChecked() && input.SkillCode == 16) return updateListByLeaderSkill(input);
        if(skill_lifesparkle_check.isChecked() && input.SkillCode == 25) return updateListByLeaderSkill(input);
        if(skill_tricolorsynergy_check.isChecked() && input.SkillCode == 26) return updateListByLeaderSkill(input);
        if(skill_coordinate_check.isChecked() && input.SkillCode == 27) return updateListByLeaderSkill(input);
        if(skill_tuning_check.isChecked() && input.SkillCode == 31) return updateListByLeaderSkill(input);
        if(skill_longact_check.isChecked() && input.SkillCode == 28) return updateListByLeaderSkill(input);
        if(skill_flickact_check.isChecked() && input.SkillCode == 29) return updateListByLeaderSkill(input);
        if(skill_slideact_check.isChecked() && input.SkillCode == 30) return updateListByLeaderSkill(input);
        if(skill_ensemble_check.isChecked() && (input.SkillCode >= 32 && input.SkillCode <= 34)) return updateListByLeaderSkill(input);
        if(skill_vocalmotif_check.isChecked() && input.SkillCode == 35) return updateListByLeaderSkill(input);
        if(skill_dancemotif_check.isChecked() && input.SkillCode == 36) return updateListByLeaderSkill(input);
        if(skill_visualmotif_check.isChecked() && input.SkillCode == 37) return updateListByLeaderSkill(input);
        if(skill_tricolorsymphony_check.isChecked() && input.SkillCode == 38) return updateListByLeaderSkill(input);
        if(skill_alternate_check.isChecked() && input.SkillCode == 39) return updateListByLeaderSkill(input);
        if(!skill_scorebonus_check.isChecked() && !skill_combobonus_check.isChecked() && !skill_combosupport_check.isChecked() &&
                !skill_liferecovery_check.isChecked() && !skill_damageguard_check.isChecked() && !skill_overload_check.isChecked() &&
                !skill_concentration_check.isChecked() && !skill_skillboost_check.isChecked() && !skill_focus_check.isChecked() &&
                !skill_allrounder_check.isChecked() && !skill_encore_check.isChecked() && !skill_lifesparkle_check.isChecked() &&
                !skill_tricolorsynergy_check.isChecked() && !skill_coordinate_check.isChecked() && !skill_tuning_check.isChecked() &&
                !skill_longact_check.isChecked() && !skill_flickact_check.isChecked() && !skill_slideact_check.isChecked() &&
                !skill_ensemble_check.isChecked() && !skill_vocalmotif_check.isChecked() && !skill_dancemotif_check.isChecked() &&
                !skill_visualmotif_check.isChecked() && !skill_tricolorsymphony_check.isChecked() && !skill_alternate_check.isChecked()) return updateListByLeaderSkill(input);
        return false;
    }

    private boolean updateListByLeaderSkill(Card input) {
        if(centerskill_voice.isChecked() && (input.CenterSkillCode == 1 || input.CenterSkillCode == 6 || input.CenterSkillCode == 11 || input.CenterSkillCode == 16 || input.CenterSkillCode == 24 || input.CenterSkillCode == 29
                || input.CenterSkillCode == 34 || input.CenterSkillCode == 39 || input.CenterSkillCode == 47 || input.CenterSkillCode == 52 || input.CenterSkillCode == 57 || input.CenterSkillCode == 62)) return true;
        if(centerskill_step.isChecked() && (input.CenterSkillCode == 2 || input.CenterSkillCode == 7 || input.CenterSkillCode == 12 || input.CenterSkillCode == 17 || input.CenterSkillCode == 25 || input.CenterSkillCode == 30
                || input.CenterSkillCode == 35 || input.CenterSkillCode == 40 || input.CenterSkillCode == 48 || input.CenterSkillCode == 53 || input.CenterSkillCode == 58 || input.CenterSkillCode == 63)) return true;
        if(centerskill_make.isChecked() && (input.CenterSkillCode == 3 || input.CenterSkillCode == 8 || input.CenterSkillCode == 13 || input.CenterSkillCode == 18 || input.CenterSkillCode == 26 || input.CenterSkillCode == 31
                || input.CenterSkillCode == 36 || input.CenterSkillCode == 41 || input.CenterSkillCode == 49 || input.CenterSkillCode == 54 || input.CenterSkillCode == 59 || input.CenterSkillCode == 64)) return true;
        if(centerskill_brilliance.isChecked() && (input.CenterSkillCode == 4 || input.CenterSkillCode == 9 || input.CenterSkillCode == 14 || input.CenterSkillCode == 19 || input.CenterSkillCode == 27 || input.CenterSkillCode == 32
                || input.CenterSkillCode == 37 || input.CenterSkillCode == 42 || input.CenterSkillCode == 50 || input.CenterSkillCode == 55 || input.CenterSkillCode == 60 || input.CenterSkillCode == 65)) return true;
        if(centerskill_energy.isChecked() && (input.CenterSkillCode == 5 || input.CenterSkillCode == 10 || input.CenterSkillCode == 15 || input.CenterSkillCode == 20 || input.CenterSkillCode == 28 || input.CenterSkillCode == 33
                || input.CenterSkillCode == 38 || input.CenterSkillCode == 43 || input.CenterSkillCode == 51 || input.CenterSkillCode == 56 || input.CenterSkillCode == 61 || input.CenterSkillCode == 66)) return true;
        if(centerskill_ability.isChecked() && ((input.CenterSkillCode >= 21 && input.CenterSkillCode <= 23) || (input.CenterSkillCode >= 44 && input.CenterSkillCode <= 46) ||
                (input.CenterSkillCode >= 67 && input.CenterSkillCode <= 69))) return true;
        if(centerskill_tricolorvoice.isChecked() && (input.CenterSkillCode == 70 || input.CenterSkillCode == 82)) return true;
        if(centerskill_tricolorstep.isChecked() && (input.CenterSkillCode == 71 || input.CenterSkillCode == 83)) return true;
        if(centerskill_tricolormake.isChecked() && (input.CenterSkillCode == 72 || input.CenterSkillCode == 84)) return true;
        if(centerskill_tricolorability.isChecked() && (input.CenterSkillCode == 73 || input.CenterSkillCode == 114)) return true;
        if(centerskill_princess.isChecked() && ((input.CenterSkillCode >= 74 && input.CenterSkillCode <= 76) || (input.CenterSkillCode >= 86 && input.CenterSkillCode <= 88))) return true;
        if(centerskill_cheer.isChecked() && ((input.CenterSkillCode >= 77 && input.CenterSkillCode <= 79))) return true;
        if(centerskill_fortunepresent.isChecked() && (input.CenterSkillCode == 80 || input.CenterSkillCode == 85)) return true;
        if(centerskill_cinderellacharm.isChecked() && (input.CenterSkillCode == 81 || input.CenterSkillCode == 115)) return true;
        if(centerskill_cutecool.isChecked() && (input.CenterSkillCode == 91 || input.CenterSkillCode == 109 || input.CenterSkillCode == 89)) return true;
        if(centerskill_cutepassion.isChecked() && (input.CenterSkillCode == 90 || input.CenterSkillCode == 108 || input.CenterSkillCode == 93 || input.CenterSkillCode == 111)) return true;
        if(centerskill_coolpassion.isChecked() && (input.CenterSkillCode == 92 || input.CenterSkillCode == 110 || input.CenterSkillCode == 94)) return true;
        if(centerskill_unison.isChecked() && (input.CenterSkillCode == 101 || input.CenterSkillCode == 102 || input.CenterSkillCode == 102 || input.CenterSkillCode == 103)) return true;
        if(centerskill_resonantvoice.isChecked() &&input.CenterSkillCode == 104) return true;
        if(centerskill_resonantstep.isChecked() &&input.CenterSkillCode == 105) return true;
        if(centerskill_resonantmake.isChecked() &&input.CenterSkillCode == 106) return true;
        if(centerskill_cinderellayell.isChecked() &&input.CenterSkillCode == 113) return true;
        if(centerskill_worldlevel.isChecked() &&input.CenterSkillCode == 116) return true;
        if(!centerskill_voice.isChecked() && !centerskill_step.isChecked() && !centerskill_make.isChecked() && !centerskill_brilliance.isChecked() && !centerskill_energy.isChecked() &&
                !centerskill_ability.isChecked() && !centerskill_tricolorvoice.isChecked() && !centerskill_tricolorstep.isChecked() && !centerskill_tricolormake.isChecked() && !centerskill_tricolorability.isChecked() &&
                !centerskill_princess.isChecked() && !centerskill_cheer.isChecked() && !centerskill_fortunepresent.isChecked() && !centerskill_cinderellacharm.isChecked() && !centerskill_cutecool.isChecked() &&
                !centerskill_cutepassion.isChecked() && !centerskill_coolpassion.isChecked() && !centerskill_unison.isChecked() && !centerskill_resonantvoice.isChecked() && !centerskill_resonantstep.isChecked() &&
                !centerskill_resonantmake.isChecked() && !centerskill_cinderellayell.isChecked() && !centerskill_worldlevel.isChecked()) return true;
        return false;
    }

}