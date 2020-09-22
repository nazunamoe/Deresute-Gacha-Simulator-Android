package com.nazunamoe.deresutegachasimulatorm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.ArrayList;

public class GachaListAdapter extends RecyclerView.Adapter<GachaListAdapter.ViewHolder> {

    private ArrayList<Card> list;
    int size;

    CardView CardInfoView;

    ImageView CardInfo_CardImage;

    TextView CardSubName;
    TextView CardName;
    TextView CardRarity;

    TextView CardSkillTitle;
    TextView CardSkillSummary;

    TextView CardCenterSkillTitle;
    TextView CardCenterSkillSummary;

    TextView CardVocal;
    TextView CardDance;
    TextView CardVisual;
    TextView CardTotal;

    Boolean Max_Stat_Status;
    Switch Max_Stat;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView type;
        public ViewHolder(View v) {
            super(v);

            type = v.findViewById(R.id.typeView);
        }
    }

    public GachaListAdapter(ArrayList<Card> input, int width, CardView input_test2, Boolean max) {
        this.CardInfoView = input_test2;
        CardInfo_CardImage = CardInfoView.findViewById(R.id.Gacha_CardInfo_Image);

        CardSubName = CardInfoView.findViewById(R.id.Gacha_CardInfo_SubName);
        CardName = CardInfoView.findViewById(R.id.Gacha_CardInfo_Name);
        CardRarity = CardInfoView.findViewById(R.id.Gacha_CardInfo_Rarity);

        CardSkillTitle = CardInfoView.findViewById(R.id.Gacha_CardInfo_Skill_Title);
        CardSkillSummary = CardInfoView.findViewById(R.id.Gacha_CardInfo_Skill_Summary);

        CardCenterSkillTitle = CardInfoView.findViewById(R.id.Gacha_CardInfo_Skill_Center_Skill_Title);
        CardCenterSkillSummary = CardInfoView.findViewById(R.id.Gacha_CardInfo_Skill_Center_Skill_Summary);

        CardVocal = CardInfoView.findViewById(R.id.Gacha_CardInfo_Vocal);
        CardDance = CardInfoView.findViewById(R.id.Gacha_CardInfo_Dance);
        CardVisual = CardInfoView.findViewById(R.id.Gacha_CardInfo_Visual);
        CardTotal = CardInfoView.findViewById(R.id.Gacha_CardInfo_Total);

        Max_Stat = CardInfoView.findViewById(R.id.Max_Stat);

        Max_Stat_Status = max;

        this.size = (int)Math.ceil((width / 5) * 0.84);

        list = input;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.gacha_card, parent, false) ;
        GachaListAdapter.ViewHolder vh = new GachaListAdapter.ViewHolder(view) ;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(holder.itemView.getContext())
                .load("https://hidamarirhodonite.kirara.ca/icon_card/"+list.get(position).No+".png")
                .override(size)
                .into(holder.type);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(v.getContext())
                        .load("https://hidamarirhodonite.kirara.ca/icon_card/"+list.get(position).No+".png")
                        .into(CardInfo_CardImage);

                if(list.get(position).RarityInt >= 5) {
                    CardSubName.setText(list.get(position).CardName.split("］")[0]+"］");
                    CardName.setText(list.get(position).CardName.split("］")[1]);
                } else {
                    CardSubName.setText("");
                    CardName.setText(list.get(position).CardName);
                }

                CardRarity.setText(list.get(position).Rarity);

                CardSkillTitle.setText(list.get(position).SkillName);
                CardSkillSummary.setText(list.get(position).SKillExplain);

                CardCenterSkillTitle.setText(list.get(position).CenterSkillName);
                CardCenterSkillSummary.setText(list.get(position).CenterSkillExplain);

                updateStat(position, Max_Stat_Status);

                Max_Stat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        updateStat(position, isChecked);
                    }
                });
            }
        });

    }

    private void updateStat(int position, boolean input_case) {
        if(input_case) {
            CardVocal.setText(""+list.get(position).Vocal_Max);
            CardDance.setText(""+list.get(position).Dance_Max);
            CardVisual.setText(""+list.get(position).Visual_Max);
            CardTotal.setText(""+(list.get(position).Vocal_Max+list.get(position).Dance_Max+list.get(position).Visual_Max));
        } else {
            CardVocal.setText(""+list.get(position).Vocal_Min);
            CardDance.setText(""+list.get(position).Dance_Min);
            CardVisual.setText(""+list.get(position).Visual_Min);
            CardTotal.setText(""+(list.get(position).Vocal_Min+list.get(position).Dance_Min+list.get(position).Visual_Min));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Card input) {
        list.add(input);
        this.notifyDataSetChanged();
    }

    public void clearItem(){
        list.removeAll(list);
    }
}
