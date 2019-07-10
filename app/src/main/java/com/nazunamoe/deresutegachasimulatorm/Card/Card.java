package com.nazunamoe.deresutegachasimulatorm.Card;

import android.os.Parcel;

public class Card{
    public Boolean Availablity;

    public int No;
    public String CardName;
    public String CharaName;
    public String Rarity;
    public int RarityInt;
    public String Type;

    public int Hp_Min;
    public int Vocal_Min;
    public int Dance_Min;
    public int Visual_Min;

    public int Add_Min;

    public int Hp_Max;
    public int Vocal_Max;
    public int Dance_Max;
    public int Visual_Max;

    public int Add_Max;

    public String SkillName;
    public String SKillExplain;

    public String CenterSkillName;
    public String CenterSkillExplain;

    public String EventName;

    public Boolean Limited;
    public Boolean Fes;

    public Boolean EventCard;

    public Card(int no, String cardName, String charaName, String rarity, int hp_Min, int vocal_Min, int dance_Min, int visual_Min, int hp_Max, int vocal_Max, int dance_Max, int visual_Max, String skillName,
                String skillExplain, String centerSkillName, String centerSkillExplain, String eventName, Boolean limited, Boolean fes, Boolean ava){
        this.No = no;
        this.CardName = cardName;
        this.CharaName = charaName;

        this.RarityInt = Integer.parseInt(rarity);

        switch(rarity){
            case "1":{
                this.Rarity = "NORMAL";
                break;
            }
            case "2":{
                this.Rarity = "NORMAL+";
                break;
            }
            case "3":{
                this.Rarity = "RARE";
                break;
            }
            case "4":{
                this.Rarity = "RARE+";
                break;
            }
            case "5":{
                this.Rarity = "S RARE";
                break;
            }
            case "6":{
                this.Rarity = "S RARE+";
                break;
            }
            case "7":{
                this.Rarity = "SS RARE";
                break;
            }
            case "8":{
                this.Rarity = "SS RARE+";
                break;
            }
        }

        switch(No/100000){
            case 1:{
                this.Type = "CUTE";
                break;
            }
            case 2:{
                this.Type = "COOL";
                break;
            }
            case 3:{
                this.Type = "PASSION";
                break;
            }
            default:{
                this.Type = "SPECIAL";
                break;
            }
        }

        this.Hp_Min = hp_Min;
        this.Vocal_Min = vocal_Min;
        this.Dance_Min = dance_Min;
        this.Visual_Min = visual_Min;
        this.Add_Min = this.Vocal_Min + this.Dance_Min + this.Visual_Min;

        this.Hp_Max = hp_Max;
        this.Vocal_Max = vocal_Max;
        this.Dance_Max = dance_Max;
        this.Visual_Max = visual_Max;
        this.Add_Max = this.Vocal_Max + this.Dance_Max + this.Visual_Max;

        this.SkillName = skillName;
        this.SKillExplain = skillExplain;

        this.CenterSkillName = centerSkillName;
        this.CenterSkillExplain = centerSkillExplain;

        this.EventName = eventName;
        if(eventName == null){
            this.EventCard = false;
        }else{
            this.EventCard = true;
        }

        this.Limited = limited;
        this.Fes = fes;
        this.Availablity = ava;
    }
}
