package com.nazunamoe.deresutegachasimulatorm.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.nazunamoe.deresutegachasimulatorm.Card.Card;
import com.nazunamoe.deresutegachasimulatorm.R;

import java.util.LinkedHashMap;
import java.util.List;

public class GachaListAdapter extends RecyclerView.Adapter<GachaListAdapter.ViewHolder> {

    private List<Integer> list;
    private LinkedHashMap<Integer,Card> whole_list;
    int size;

    CardView CardInfoView;
    LinearLayout CardLinearLayout;

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
    Boolean Training_Status;

    Switch Max_Stat;
    Switch Training;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView type;
        public ViewHolder(View v) {
            super(v);

            type = v.findViewById(R.id.typeView);
        }
    }

    public GachaListAdapter(LinkedHashMap<Integer,Card> whole_list, List<Integer> input, int width, CardView input_test2, Boolean max, Boolean training) {
        this.CardInfoView = input_test2;
        this.CardLinearLayout = input_test2.findViewById(R.id.gacharesultcardlinearlayout);

        this.Max_Stat_Status = max;
        this.Training_Status = training;

        this.size = (int)Math.ceil((width / 5) * 0.85);

        this.whole_list = whole_list;
        this.list = input;

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
        Training = CardInfoView.findViewById(R.id.Training);
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
                .load("https://hidamarirhodonite.kirara.ca/icon_card/"+list.get(position)+".png")
                .override(size)
                .into(holder.type);

        Card card = whole_list.get(list.get(position));

        updateInfo(CardInfoView, position, card);

        setColorByType(card.Type);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo(v, position, whole_list.get(list.get(position)));

            }
        });

    }

    private void setColorByType(String type) {
        switch(type) {
            case "CUTE" : {
                CardLinearLayout.setBackgroundColor(android.graphics.Color.argb(50, 199, 3, 91));
                break;
            }
            case "COOL" : {
                CardLinearLayout.setBackgroundColor(android.graphics.Color.argb(50, 3, 65, 182));
                break;
            }
            case "PASSION" : {
                CardLinearLayout.setBackgroundColor(android.graphics.Color.argb(50, 181, 111, 2));
                break;
            }
        }
    }

    private void updateInfo(View v, final int position, Card info) {

        setColorByType(info.Type);

        if(info.RarityInt >= 5) {
            CardSubName.setText(info.CardName.split("］")[0]+"］");
            CardName.setText(info.CardName.split("］")[1]);
        } else {
            CardSubName.setText("");
            CardName.setText(info.CardName);
        }

        CardRarity.setText(info.Rarity);

        CardSkillTitle.setText(info.SkillName);
        CardSkillSummary.setText(info.SKillExplain);

        CardCenterSkillTitle.setText(info.CenterSkillName);
        CardCenterSkillSummary.setText(info.CenterSkillExplain);

        update(v, Training_Status, Max_Stat_Status, info.No);

        Max_Stat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Max_Stat_Status = isChecked;
                update(CardInfoView,  Training_Status, isChecked, list.get(position));
            }
        });

        Training.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Training_Status = isChecked;
                update(CardInfoView, isChecked, Max_Stat_Status, list.get(position));
            }
        });

    }

    private void update(View v, Boolean trained, Boolean max_stat, int num) {
        updateImage(v, trained, num);
        updateStat(num, max_stat, trained);
    }

    private void updateImage(View v, Boolean trained, int num) {
        if(trained) {
            Glide.with(v.getContext())
                    .load("https://hidamarirhodonite.kirara.ca/icon_card/"+(num+1)+".png")
                    .into(CardInfo_CardImage);

        } else {
            Glide.with(v.getContext())
                    .load("https://hidamarirhodonite.kirara.ca/icon_card/"+num+".png")
                    .into(CardInfo_CardImage);
        }
        if(whole_list.get(num).RarityInt >= 5) {
            //setBackgroundImageByCard(trained, num);
        }else {
            setColorByType(whole_list.get(num).Type);
        }
    }

    private void setBackgroundImageByCard(Boolean trained, int num) {
        Glide.with(CardInfoView)
                .asBitmap()
                .load("https://hidamarirhodonite.kirara.ca/spread/"+num+".png")
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(CardLinearLayout.getResources(), resource);
                        bitmapDrawable.setGravity(Gravity.FILL_VERTICAL|Gravity.CENTER_HORIZONTAL);
                        CardLinearLayout.setBackgroundTintMode(PorterDuff.Mode.LIGHTEN);
                        CardLinearLayout.setBackground(bitmapDrawable);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    private void updateStat(int num, boolean max, boolean trained) {
        if(trained) num++;
        if(max) {
            CardVocal.setText(""+whole_list.get(num).Vocal_Max);
            CardDance.setText(""+whole_list.get(num).Dance_Max);
            CardVisual.setText(""+whole_list.get(num).Visual_Max);
            CardTotal.setText(""+(whole_list.get(num).Vocal_Max+whole_list.get(num).Dance_Max+whole_list.get(num).Visual_Max));
        } else {
            CardVocal.setText(""+whole_list.get(num).Vocal_Min);
            CardDance.setText(""+whole_list.get(num).Dance_Min);
            CardVisual.setText(""+whole_list.get(num).Visual_Min);
            CardTotal.setText(""+(whole_list.get(num).Vocal_Min+whole_list.get(num).Dance_Min+whole_list.get(num).Visual_Min));
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
        list.add(input.No);
        this.notifyDataSetChanged();
    }

    public void clearItem(){
        list.removeAll(list);
        CardVocal.setText("");
        CardDance.setText("");
        CardVisual.setText("");
        CardTotal.setText("");
        CardSubName.setText("");
        CardName.setText("");
        CardRarity.setText("");
        CardSkillTitle.setText("");
        CardSkillSummary.setText("");
        CardCenterSkillTitle.setText("");
        CardCenterSkillSummary.setText("");
        CardInfo_CardImage.setImageResource(0);
        CardLinearLayout.setBackgroundResource(0);
    }
}
