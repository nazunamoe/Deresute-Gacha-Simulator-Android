package com.nazunamoe.deresutegachasimulatorm.Class;

import com.nazunamoe.deresutegachasimulatorm.Activity.MainActivity;
import com.nazunamoe.deresutegachasimulatorm.R;

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

    public int SkillCode;
    public String SkillCategory;
    public String SkillName;
    public String SKillExplain;

    public int CenterSkillCode;
    public String CenterSkillCategory;
    public String CenterSkillName;
    public String CenterSkillExplain;

    public String EventName;

    public Boolean Limited;
    public Boolean Fes;

    public int CardCategory;

    public Card(int no, String cardName, String charaName, String rarity, int hp_Min, int vocal_Min, int dance_Min, int visual_Min, int hp_Max, int vocal_Max, int dance_Max, int visual_Max, int skillCode, String skillName,
                String skillExplain, int centerSkillCode, String centerSkillName, String centerSkillExplain, String eventName, Boolean limited, Boolean fes, Boolean ava){
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
                this.Type = getString(R.string.Cute);
                break;
            }
            case 2:{
                this.Type = getString(R.string.Cool);
                break;
            }
            case 3:{
                this.Type = getString(R.string.Passion);
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

        this.SkillCode = skillCode;

        switch(skillCode) {
            case 1: case 2:  {
                SkillCategory = getString(R.string.Skill_ScoreBonus);
                break;
            }
            case 4: {
                SkillCategory = getString(R.string.Skill_ComboBonus);
                break;
            }
            case 5: case 6: case 7: case 9:{
                SkillCategory = getString(R.string.Skill_PerfectComboSupport);
                break;
            }
            case 17: {
                SkillCategory = getString(R.string.Skill_LifeRecovery);
                break;
            }
            case 12: {
                SkillCategory = getString(R.string.Skill_DamageGuard);
                break;
            }
            case 14: {
                SkillCategory = getString(R.string.Skill_OverLoad);
                break;
            }
            case 15: {
                SkillCategory = getString(R.string.Skill_Concentration);
                break;
            }
            case 20: {
                SkillCategory = getString(R.string.Skill_SkillBoost);
                break;
            }
            case 21: case 22: case 23: {
                SkillCategory = getString(R.string.Skill_Focus);
                break;
            }
            case 24: {
                SkillCategory = getString(R.string.Skill_AllRounder);
                break;
            }
            case 16: {
                SkillCategory = getString(R.string.Skill_Encore);
                break;
            }
            case 25: {
                SkillCategory = getString(R.string.Skill_LifeSparkle);
                break;
            }
            case 26: {
                SkillCategory = getString(R.string.Skill_TricolorSynergy);
                break;
            }
            case 27: {
                SkillCategory = getString(R.string.Skill_Coordinate);
                break;
            }
            case 31: {
                SkillCategory = getString(R.string.Skill_Tuning);
                break;
            }
            case 28: {
                SkillCategory = getString(R.string.Skill_LongAct);
                break;
            }
            case 29: {
                SkillCategory = getString(R.string.Skill_FlickAct);
                break;
            }
            case 30: {
                SkillCategory = getString(R.string.Skill_SlideAct);
                break;
            }
            case 32: case 33: case 34: {
                SkillCategory = getString(R.string.Skill_Ensemble);
                break;
            }
            case 35: {
                SkillCategory = getString(R.string.Skill_VocalMotif);
                break;
            }
            case 36: {
                SkillCategory = getString(R.string.Skill_DanceMotif);
                break;
            }
            case 37: {
                SkillCategory = getString(R.string.Skill_VisualMotif);
                break;
            }
            case 38: {
                SkillCategory = getString(R.string.Skill_TricolorSymphony);
                break;
            }
            case 39: {
                SkillCategory = getString(R.string.Skill_Alternate);
                break;
            }
            case 40: {
                SkillCategory = getString(R.string.Skill_Refrain);
                break;
            }
        }

        this.SkillName = skillName;
        this.SKillExplain = skillExplain;

        this.CenterSkillCode = centerSkillCode;

        switch(centerSkillCode) {
            case 1: case 6: case 11: {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_VocalUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_VocalUp_Explain_1);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_VocalUp_Explain_1);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_VocalUp_Explain_1);
                        break;
                    }
                }
                break;
            }
            case 24: case 29: case 34: {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_VocalUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_VocalUp_Explain_2);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_VocalUp_Explain_2);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_VocalUp_Explain_2);
                        break;
                    }
                }
                break;
            }
            case 47: case 52: case 57: {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_VocalUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_VocalUp_Explain_3);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_VocalUp_Explain_3);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_VocalUp_Explain_3);
                        break;
                    }
                }
                break;
            }

            case 2: case 7: case 12:{
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_DanceUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_DanceUp_Explain_1);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_DanceUp_Explain_1);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_DanceUp_Explain_1);
                        break;
                    }
                }
                break;
            }
            case 25: case 30: case 35: {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_DanceUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_DanceUp_Explain_2);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_DanceUp_Explain_2);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_DanceUp_Explain_2);
                        break;
                    }
                }
                break;
            }
            case 48: case 53: case 58: {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_DanceUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_DanceUp_Explain_3);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_DanceUp_Explain_3);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_DanceUp_Explain_3);
                        break;
                    }
                }
                break;
            }

            case 3: case 8: case 13: {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_VisualUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_DanceUp_Explain_1);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_DanceUp_Explain_1);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_DanceUp_Explain_1);
                        break;
                    }
                }
                break;
            }
            case 26: case 31: case 36: {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_VisualUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_DanceUp_Explain_2);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_DanceUp_Explain_2);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_DanceUp_Explain_2);
                        break;
                    }
                }
                break;
            }
            case 49: case 54: case 59: {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_VisualUp);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_DanceUp_Explain_3);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_DanceUp_Explain_3);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_DanceUp_Explain_3);
                        break;
                    }
                }
                break;
            }

            case 4: case 9: case 14:  {
                CenterSkillCategory = Type + " " + getString(R.string.LeaderSkill_Brilliance);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Brilliance_Explain_1);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Brilliance_Explain_1);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Brilliance_Explain_1);
                        break;
                    }
                }
                break;
            }
            case 27: case 32: case 37: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Brilliance);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Brilliance_Explain_2);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Brilliance_Explain_2);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Brilliance_Explain_2);
                        break;
                    }
                }
                break;
            }
            case 50: case 55: case 60: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Brilliance);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Brilliance_Explain_3);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Brilliance_Explain_3);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Brilliance_Explain_3);
                        break;
                    }
                }
                break;
            }

            case 5: case 10: case 15: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_LifeUp_Normal);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_LifeUp_Normal_Explain_1);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_LifeUp_Normal_Explain_1);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_LifeUp_Normal_Explain_1);
                        break;
                    }
                }
                break;
            }
            case 28: case 33: case 38: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_LifeUp_Normal);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_LifeUp_Normal_Explain_2);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_LifeUp_Normal_Explain_2);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_LifeUp_Normal_Explain_2);
                        break;
                    }
                }
                break;
            }
            case 51: case 56: case 61: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_LifeUp_Normal);
                switch(No/100000){
                    case 1:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cute_LifeUp_Normal_Explain_3);
                        break;
                    }
                    case 2:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Cool_LifeUp_Normal_Explain_3);
                        break;
                    }
                    case 3:{
                        CenterSkillExplain = getString(R.string.LeaderSkill_Passion_LifeUp_Normal_Explain_3);
                        break;
                    }
                }
                break;
            }

            case 16: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_VocalUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_VocalUp_Explain_1);
                break;
            }
            case 39: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_VocalUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_VocalUp_Explain_2);
                break;
            }
            case 62: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_VocalUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_VocalUp_Explain_3);
                break;
            }

            case 17: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_DanceUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_DanceUp_Explain_1);
                break;
            }
            case 40: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_DanceUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_DanceUp_Explain_2);
                break;
            }
            case 63: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_DanceUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_DanceUp_Explain_3);
                break;
            }

            case 18: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_VisualUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_VisualUp_Explain_1);
                break;
            }
            case 41: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_VisualUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_VisualUp_Explain_2);
                break;
            }
            case 64: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_VisualUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_VisualUp_Explain_3);
                break;
            }

            case 19: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_Brilliance);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_Brilliance_Explain_1);
                break;
            }
            case 42: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_Brilliance);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_Brilliance_Explain_2);
                break;
            }
            case 65: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_Brilliance);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_Brilliance_Explain_3);
                break;
            }

            case 20: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_LifeUp_Normal);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_LifeUp_Normal_Explain_1);
                break;
            }
            case 43: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_LifeUp_Normal);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_LifeUp_Normal_Explain_2);
                break;
            }
            case 66: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Shiny) + " " + getString(R.string.LeaderSkill_LifeUp_Normal);
                CenterSkillExplain = getString(R.string.LeaderSkill_Shiny_LifeUp_Normal_Explain_3);
                break;
            }

            case 70: {
                CenterSkillCategory = getString(R.string.LeaderSkill_TricolorVocalUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_TricolorVocalUp_Explain_1);
                break;
            }
            case 82: {
                CenterSkillCategory = getString(R.string.LeaderSkill_TricolorVocalUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_TricolorVocalUp_Explain_2);
                break;
            }

            case 71: {
                CenterSkillCategory = getString(R.string.LeaderSkill_TricolorDanceUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_TricolorDanceUp_Explain_1);
                break;
            }
            case 83: {
                CenterSkillCategory = getString(R.string.LeaderSkill_TricolorDanceUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_TricolorDanceUp_Explain_2);
                break;
            }

            case 72: {
                CenterSkillCategory = getString(R.string.LeaderSkill_TricolorVisualUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_TricolorVisualUp_Explain_1);
                break;
            }
            case 84: {
                CenterSkillCategory = getString(R.string.LeaderSkill_TricolorVisualUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_TricolorVisualUp_Explain_2);
                break;
            }

            case 73: {
                CenterSkillCategory = getString(R.string.LeaderSkill_TricolorSkillUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_TricolorSkillUp_Explain_1);
                break;
            }
            case 114: {
                CenterSkillCategory = getString(R.string.LeaderSkill_TricolorSkillUp);
                CenterSkillExplain = getString(R.string.LeaderSkill_TricolorSkillUp_Explain_2);
                break;
            }

            case 74: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Princess);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Princess_Explain_1);
                break;
            }
            case 75: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Princess);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Princess_Explain_1);
                break;
            }
            case 76: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Princess);
                CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Princess_Explain_1);
                break;
            }
            case 86: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Princess);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Princess_Explain_2);
                break;
            }
            case 87: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Princess);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Princess_Explain_2);
                break;
            }
            case 88: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Princess);
                CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Princess_Explain_2);
                break;
            }

            case 77: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_LifeUp_Cheer);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cute_LifeUp_Cheer_Explain);
                break;
            }
            case 78: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_LifeUp_Cheer);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cool_LifeUp_Cheer_Explain);
                break;
            }
            case 79: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_LifeUp_Cheer);
                CenterSkillExplain = getString(R.string.LeaderSkill_Passion_LifeUp_Cheer_Explain);
                break;
            }

            case 80: case 85: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Fortune_Present);
                CenterSkillExplain = getString(R.string.LeaderSkill_Fortune_Present);
                break;
            }

            case 81: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cinderella_Charm);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cinderella_Charm_Explain_1);
                break;
            }
            case 115: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cinderella_Charm);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cinderella_Charm_Explain_2);
                break;
            }

            case 89: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cute_Cool);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Cool_Explain_1);
                break;
            }
           case 107: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cute_Cool);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Cool_Explain_2);
                break;
            }

            case 90: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cute_Passion);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Passion_Explain_1);
                break;
            }
            case 108: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cute_Passion);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Passion_Explain_2);
                break;
            }

            case 91: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cool_Cute);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Cute_Explain_1);
                break;
            }
            case 109: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cool_Cute);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Cute_Explain_2);
                break;
            }

            case 92: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cool_Passion);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Passion_Explain_1);
                break;
            }
            case 110: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cool_Passion);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Passion_Explain_2);
                break;
            }

            case 93: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Passion_Cute);
                CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Cute_Explain_1);
                break;
            }
            case 111: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Passion_Cute);
                CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Cute_Explain_2);
                break;
            }

            case 94: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Passion_Cool);
                CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Cool_Explain_1);
                break;
            }
            case 112: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Passion_Cool);
                CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Cool_Explain_2);
                break;
            }

            case 101: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Unison);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cute_Unison_Explain);
                break;
            }
            case 102: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Unison);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cool_Unison_Explain);
                break;
            }
            case 103: {
                CenterSkillCategory = this.Type + " " + getString(R.string.LeaderSkill_Unison);
                CenterSkillExplain = getString(R.string.LeaderSkill_Passion_Unison_Explain);
                break;
            }

            case 104: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Resonant_Voice);
                CenterSkillExplain = getString(R.string.LeaderSkill_Resonant_Voice_Explain);
                break;
            }
            case 105: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Resonant_Dance);
                CenterSkillExplain = getString(R.string.LeaderSkill_Resonant_Dance_Explain);
                break;
            }
            case 106: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Resonant_Visual);
                CenterSkillExplain = getString(R.string.LeaderSkill_Resonant_Visual_Explain);
                break;
            }
            case 113: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cinderella_Yell);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cinderella_Yell_Explain);
                break;
            }
            case 116: {
                CenterSkillCategory = getString(R.string.LeaderSkill_WorldLevel);
                CenterSkillExplain = getString(R.string.LeaderSkill_WorldLevel_Explain);
                break;
            }
            case 117: {
                CenterSkillCategory = getString(R.string.LeaderSkill_Cinderella_Wish);
                CenterSkillExplain = getString(R.string.LeaderSkill_Cinderella_Wish_Explain);
                break;
            }
        }

        this.CenterSkillName = centerSkillName;


        this.EventName = eventName;

        // CardCategory 0 = 통상 / 1 = 이벤트 / 2 = 월말한정 / 3 = 페스

        CardCategory = 0;
        if(eventName != null) CardCategory = 1;
        if(limited) CardCategory = 2;
        if(fes) CardCategory = 3;

        this.Availablity = ava;

    }

    private String getString(int id) {
        return MainActivity.getResourses().getString(id);
    }
}
