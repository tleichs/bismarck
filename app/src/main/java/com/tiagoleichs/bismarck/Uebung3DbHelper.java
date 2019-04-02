package com.tiagoleichs.bismarck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tiagoleichs.bismarck.uebung3Contract.*;

import java.util.ArrayList;

public class Uebung3DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyQuiz22.db";
    private static final int DATABASE_VERSION = 7;

    private  SQLiteDatabase db;

    public Uebung3DbHelper(Context context){
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
               uebung3Contract.Uebung3Table.TABLE_NAME + " ( " +
                Uebung3Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Uebung3Table.COLUMN_VERB + " TEXT, " +
                Uebung3Table.COLUMN_ICH + " TEXT, " +
               Uebung3Table.COLUMN_DU + " TEXT, " +
                Uebung3Table.COLUMN_ER + " TEXT, " +
               Uebung3Table.COLUMN_WIR + " TEXT, " +
                Uebung3Table.COLUMN_IHR + " TEXT, " +
                Uebung3Table.COLUMN_SIE + " TEXT, " +
                Uebung3Table.COLUMN_ANSWER1 + " TEXT, " +
                Uebung3Table.COLUMN_ANSWER2 + " TEXT, " +
                Uebung3Table.COLUMN_ANSWER3 + " TEXT, " +
                Uebung3Table.COLUMN_KAPITEL + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    private void fillQuestionsTable() {
        Uebung3 q1 = new Uebung3("heißen", "", "heißt", "","",
                "heißt", "heißen", "heiße", "heißt", "heißen", Uebung3.KAPITEL1);
        addUebung3(q1);
        Uebung3 q2 = new Uebung3("sein", "", "", "","sind ",
                "seid", "sind", "bin", "bist", "ist", Uebung3.KAPITEL1);
        addUebung3(q2);



    }

    public void addUebung3 (Uebung3 uebung3){
        ContentValues cv = new ContentValues();
        cv.put(uebung3Contract.Uebung3Table.COLUMN_VERB, uebung3.getVerb());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_ICH, uebung3.getIch());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_DU, uebung3.getDu());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_ER, uebung3.getEr());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_WIR, uebung3.getWir());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_IHR, uebung3.getIhr());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_SIE, uebung3.getSie());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_ANSWER1, uebung3.getAnswer1());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_ANSWER2, uebung3.getAnswer2());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_ANSWER3, uebung3.getAnswer3());
        cv.put(uebung3Contract.Uebung3Table.COLUMN_KAPITEL, uebung3.getKapitel());

        db.insert(uebung3Contract.Uebung3Table.TABLE_NAME, null, cv);

    }

    public ArrayList<Uebung3> getUebung3 (String kapitel){
        ArrayList<Uebung3> uebung3List = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[] {kapitel};
        Cursor c = db.rawQuery("SELECT * FROM " + uebung3Contract.Uebung3Table.TABLE_NAME +
                " WHERE " + uebung3Contract.Uebung3Table.COLUMN_KAPITEL + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                Uebung3 uebung3 = new Uebung3();
                uebung3.setVerb(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_VERB)));
                uebung3.setIch(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_ICH)));
                uebung3.setDu(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_DU)));
                uebung3.setEr(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_ER)));
                uebung3.setWir(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_WIR)));
                uebung3.setIhr(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_IHR)));
                uebung3.setSie(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_SIE)));
                uebung3.setAnswer1(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_ANSWER1)));
                uebung3.setAnswer2(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_ANSWER2)));
                uebung3.setAnswer3(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_ANSWER3)));
                uebung3.setKapitel(c.getString(c.getColumnIndex(uebung3Contract.Uebung3Table.COLUMN_KAPITEL)));
                uebung3List.add(uebung3);
            } while (c.moveToNext());
        }
        c.close();
        return uebung3List;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + uebung3Contract.Uebung3Table.TABLE_NAME);
        onCreate(db);

    }
}
