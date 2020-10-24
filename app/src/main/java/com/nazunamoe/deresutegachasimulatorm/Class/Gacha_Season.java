package com.nazunamoe.deresutegachasimulatorm.Class;

import java.util.ArrayList;

public class Gacha_Season {
    public String Gacha_season_title;
    public String Gacha_season_start_date;
    public String Gacha_season_end_date;

    public ArrayList<Card> Gacha_season_card_list;

    public Gacha_Season(String title, String start_date, String end_date, ArrayList<Card> card_list) {
        this.Gacha_season_title = title;
        this.Gacha_season_start_date = start_date;
        this.Gacha_season_end_date = end_date;
        this.Gacha_season_card_list = card_list;
    }

}
