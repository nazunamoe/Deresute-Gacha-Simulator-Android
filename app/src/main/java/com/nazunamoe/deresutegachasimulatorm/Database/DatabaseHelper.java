package com.nazunamoe.deresutegachasimulatorm.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nazunamoe.deresutegachasimulatorm.Card.Card;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "10056000.sqlite";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
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
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
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
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public Card getResult(int id){
        Card result = null;
        Cursor cursor = mDataBase.rawQuery("SELECT * FROM card_info WHERE card_info.id=100573",null);
        cursor.moveToFirst();
        /*
            public Card(int no, String cardName, String charaName, String rarity, int hp_Min, int vocal_Min, int dance_Min, int visual_Min, int hp_Max, int vocal_Max, int dance_Max, int visual_Max, String skillName,
                String skillExplain, String centerSkillName, String centerSkillExplain, String eventName, Boolean limited, Boolean fes){
         */
        int cardno = cursor.getInt(0);
        String cardname = cursor.getString(1);
        String charaname = cursor.getString(2);
        String rarity = cursor.getString(3);

        int hp_min = cursor.getInt(5);
        int vo_min = cursor.getInt(6);
        int da_min = cursor.getInt(7);
        int vi_min = cursor.getInt(8);

        int hp_max = cursor.getInt(10);
        int vo_max = cursor.getInt(11);
        int da_max = cursor.getInt(12);
        int vi_max = cursor.getInt(13);

        String skillname = cursor.getString(15);
        String skillexplain = cursor.getString(16);
        String centerskillname = cursor.getString(17);
        String centerskillexplain = cursor.getString(18);

        String eventname = cursor.getString(19);

        boolean limited = cursor.getInt(20) > 0;
        boolean fes = cursor.getInt(21) > 0;

        result = new Card(cardno, cardname, charaname, rarity
                    , hp_min, vo_min, da_min, vi_min, hp_max, vo_max, da_max, vi_max
                    , skillname, skillexplain, centerskillname, centerskillexplain
                    , eventname, limited, fes);
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }
}