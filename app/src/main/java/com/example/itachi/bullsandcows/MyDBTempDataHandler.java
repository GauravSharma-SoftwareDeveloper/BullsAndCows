package com.example.itachi.bullsandcows;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by itachi on 24/5/16.
 */
public class MyDBTempDataHandler extends SQLiteOpenHelper {

    private static final char colSeprator = '|';
    private static final char rowSeprator = '$';


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="bullsandcowstempdata.db";
    private static final String TABLE_NAME="tempdata";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_SNO="sno";
    private static final String COLUMN_GUESSED_NO="guessedno";
    private static final String COLUMN_BULLS="bulls";
    private static final String COLUMN_COWS="cows";



    public MyDBTempDataHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        //Log.i("gaurav","oncreate:");




        String query="CREATE TABLE "+ TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_SNO+ " INTEGER, " +
                COLUMN_GUESSED_NO  + " INTEGER, " +
                COLUMN_BULLS+ " INTEGER, " +
                COLUMN_COWS	+ " INTEGER " +
                ");" ;

        //String query="CREATE TABLE prod(_id INTEGER AUTOINCREMENT, productname TEXT);";
        db.execSQL(query);

    }

    public void addRow(int sNo,int guessedNo,int bulls,int cows){
        SQLiteDatabase db = getWritableDatabase();
        //String query="";


        ContentValues values = new ContentValues();
        values.put(COLUMN_SNO,sNo);
        values.put(COLUMN_GUESSED_NO,guessedNo);
        values.put(COLUMN_BULLS,bulls);
        values.put(COLUMN_COWS,cows);

        db.insert(TABLE_NAME, null, values);
        db.close();


    }


    public void emptyDatabase(){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public String databaseToString(){
        String result="";

        SQLiteDatabase db = getReadableDatabase();
        //Log.i("gaurav","database to string::"+db);
        //onCreate(db);
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1 ";


        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_SNO)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_SNO));
                result += colSeprator;
            }

            if (c.getString(c.getColumnIndex(COLUMN_GUESSED_NO)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_GUESSED_NO));
                result += colSeprator;
            }

            if (c.getString(c.getColumnIndex(COLUMN_BULLS)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_BULLS));
                result += colSeprator;
            }

            if (c.getString(c.getColumnIndex(COLUMN_COWS)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_COWS));
                result += colSeprator;
            }
            //Log.i("Gaurav1",result);

            result += rowSeprator;
            c.moveToNext();

           // Log.i("gaurav",result);

        }
        db.close();

        emptyDatabase();
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
