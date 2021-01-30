package com.nazunamoe.deresutegachasimulatorm.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nazunamoe.deresutegachasimulatorm.Class.Card;
import com.nazunamoe.deresutegachasimulatorm.Class.Gacha_Season;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "10081300.sqlite";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            System.out.println("Database File : "+DB_PATH);
        }
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        File dbFile = new File(DB_PATH + DB_NAME);
        if (dbFile.exists())
            dbFile.delete();

        copyDataBase();
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public LinkedHashMap<Integer, Card> getAllCardList_temp() {
        LinkedHashMap<Integer, Card> result = new LinkedHashMap<>();
        Cursor cursor = mDataBase.rawQuery("SELECT * " +
                "FROM card_info",null);
        cursor.moveToFirst();
        for(int i = 0 ; i < cursor.getCount(); i++) {
            Card temp = cursorToCard(cursor);
            result.put(temp.No, temp);
            cursor.moveToNext();
        }
        return result;
    }

    public int getFesLimited() {
        Cursor cursor = mDataBase.rawQuery("SELECT * " +
                "FROM card_info " +
                "WHERE id = 100573",null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("ava"));
    }

    public void setFesLimited(int limited) {
        mDataBase.execSQL("UPDATE card_info " +
                "SET ava = " + limited + " " +
                "WHERE fes = 1");
    }

    public int getSeasonLimited(int seasonid) {
        Cursor cursor = mDataBase.rawQuery("SELECT * " +
                "FROM limited_gacha_info " +
                "WHERE id = " + seasonid,null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("ava"));
    }

    public void setSeasonLimited(int seasonid, int limited) {
        mDataBase.execSQL("UPDATE limited_gacha_info " +
                "SET ava = " + limited + " " +
                "WHERE id = " + seasonid);
    }

     public void setCardLimited(Card input, int limited) {
        mDataBase.execSQL("UPDATE card_info " +
                "SET ava = " + limited + " " +
                "WHERE id = " + input.No);
    }

    public ArrayList<Card> getAllCardList() {
        ArrayList<Card> result = new ArrayList<>();
        Cursor cursor = mDataBase.rawQuery("SELECT * " +
                "FROM card_info",null);
        cursor.moveToFirst();
        for(int i = 0 ; i < cursor.getCount(); i++) {
            result.add(cursorToCard(cursor));
            cursor.moveToNext();
        }
        return result;
    }

    public ArrayList<Gacha_Season> getAllSeasons() {
        ArrayList<Gacha_Season> seasonlist = new ArrayList<>();
        Cursor cursor = mDataBase.rawQuery("SELECT * " +
                "FROM limited_gacha_info ",null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            ArrayList<Card> card_list = new ArrayList<>();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("name"));
            String start_date = cursor.getString(cursor.getColumnIndex("start_date"));
            String end_date = cursor.getString(cursor.getColumnIndex("end_date"));
            int avaint = cursor.getInt(cursor.getColumnIndex("ava"));
            Cursor cursor2 = mDataBase.rawQuery("SELECT * " +
                    "FROM limited_gacha_card_list " +
                    "WHERE id = " + cursor.getInt(cursor.getColumnIndex("id")) ,null);
            cursor2.moveToFirst();
            for(int j = 0; j < cursor2.getCount(); j++) {
                card_list.add(getCard(cursor2.getInt(cursor2.getColumnIndex("card_id"))));
                cursor2.moveToNext();
            }
            Gacha_Season temp = new Gacha_Season(id,title,start_date,end_date,card_list,avaint);
            seasonlist.add(temp);
            cursor.moveToNext();
        }
        return seasonlist;
    }

    public Card getCard(int id) {
        Cursor cursor = mDataBase.rawQuery("SELECT * " +
                "FROM card_info " +
                "WHERE id = " + id,null);
        cursor.moveToFirst();
        return cursorToCard(cursor);
    }

    public Card getRarityCard(int Rarity) {
        Cursor cursor = mDataBase.rawQuery("SELECT * " +
                "FROM card_info " +
                "WHERE rarity = " + Rarity + " " +
                "AND ava = 0 " +
                "AND id % 2 = 1 " +
                "AND event_name IS NULL " +
                "ORDER BY RANDOM() LIMIT 1",null);
        cursor.moveToFirst();
        return cursorToCard(cursor);
    }

    public Card cursorToCard(Cursor cursor) {
        Card result;

        /*
            public Card(int no, String cardName, String charaName, String rarity, int hp_Min, int vocal_Min, int dance_Min, int visual_Min, int hp_Max, int vocal_Max, int dance_Max, int visual_Max, String skillName,
                String skillExplain, String centerSkillName, String centerSkillExplain, String eventName, Boolean limited, Boolean fes){
         */
        int cardno = cursor.getInt(cursor.getColumnIndex("id"));
        String cardname = cursor.getString(cursor.getColumnIndex("card_name"));
        String charaname = cursor.getString(cursor.getColumnIndex("chara_name"));
        String rarity = cursor.getString(cursor.getColumnIndex("rarity"));

        int hp_min = cursor.getInt(cursor.getColumnIndex("hp_min"));
        int vo_min = cursor.getInt(cursor.getColumnIndex("vocal_min"));
        int da_min = cursor.getInt(cursor.getColumnIndex("dance_min"));
        int vi_min = cursor.getInt(cursor.getColumnIndex("visual_min"));

        int hp_max = cursor.getInt(cursor.getColumnIndex("hp_max"));
        int vo_max = cursor.getInt(cursor.getColumnIndex("vocal_max"));
        int da_max = cursor.getInt(cursor.getColumnIndex("dance_max"));
        int vi_max = cursor.getInt(cursor.getColumnIndex("visual_max"));

        int skillcode = cursor.getInt(cursor.getColumnIndex("skill_type"));

        String skillname = cursor.getString(cursor.getColumnIndex("skill_name"));
        String skillexplain = cursor.getString(cursor.getColumnIndex("skill_explain"));

        int centerskillcode = cursor.getInt(cursor.getColumnIndex("leader_skill_id"));

        String centerskillname = cursor.getString(cursor.getColumnIndex("leader_skill_name"));
        String centerskillexplain = cursor.getString(cursor.getColumnIndex("leader_skill_explain"));

        String eventname = cursor.getString(cursor.getColumnIndex("event_name"));

        int limitedint = cursor.getInt(cursor.getColumnIndex("limited_month"));
        int fesint = cursor.getInt(cursor.getColumnIndex("fes"));

        boolean limited = false;
        limited = limitedint == 1;

        boolean fes = false;
        fes = fesint == 1;

        boolean ava;

        int avaint = cursor.getInt(cursor.getColumnIndex("ava"));

        ava = avaint != 0;

        // 필요한 정보를 모두 변수에 연결.
        result = new Card(cardno, cardname, charaname, rarity
                , hp_min, vo_min, da_min, vi_min, hp_max, vo_max, da_max, vi_max, skillcode
                , skillname, skillexplain, centerskillcode, centerskillname, centerskillexplain
                , eventname, limited, fes, ava);
        return result;
    }

}