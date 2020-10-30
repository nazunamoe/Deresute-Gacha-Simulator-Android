package com.nazunamoe.deresutegachasimulatorm.Class;

import java.util.ArrayList;

public class Gacha_Season {
    public int id;
    public String Gacha_season_title;
    public String Gacha_season_start_date;
    public String Gacha_season_end_date;

    public ArrayList<Card> Gacha_season_card_list;

    public boolean Availability;

    public Gacha_Season(int id, String title, String start_date, String end_date, ArrayList<Card> card_list, int ava) {
        this.id = id;
        this.Gacha_season_title = title;
        this.Gacha_season_start_date = start_date;
        this.Gacha_season_end_date = end_date;
        this.Gacha_season_card_list = card_list;

        this.Availability = ava != 1;
    }
}
