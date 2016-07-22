package com.example.itachi.bullsandcows;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * Created by itachi on 26/5/16.
 */
public class MyDBHighscoreHandler extends SQLiteOpenHelper{

    private static final char colSeprator = '|';
    private static final char rowSeprator = '$';


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="bullsandcowshighscore.db";
    private static final String TABLE_NAME="highscore";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_SCORE="score";
    private static final String COLUMN_DATE="date";
    private static final String COLUMN_TIME="time";





    public MyDBHighscoreHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE "+ TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_NAME+ " TEXT, " +
                COLUMN_SCORE  + " INTEGER, " +
                COLUMN_DATE+ " TEXT, " +
                COLUMN_TIME	+ " TEXT " +
                ");" ;

        //String query="CREATE TABLE prod(_id INTEGER AUTOINCREMENT, productname TEXT);";
        db.execSQL(query);

    }

    public void addRow(String name,int score){
        SQLiteDatabase db = getWritableDatabase();
        //String query="";


        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_SCORE,score);


        long myDate=System.currentTimeMillis();
        SimpleDateFormat sdfDate= new SimpleDateFormat("MMM MM dd ,yyyy");
        SimpleDateFormat sdfTime= new SimpleDateFormat("HH:mm:ss a");
        String dateString=sdfDate.format(myDate);
        String timeString=sdfTime.format(myDate);



        values.put(COLUMN_DATE, dateString);
        values.put(COLUMN_TIME, timeString);

        db.insert(TABLE_NAME, null, values);
        db.close();

       // Log.i("gaurav","+"+name+"+"+score+"+"+dateString+"+"+timeString);

    }


    public void emptyDatabase(){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public String databaseToString(){
        String result="";

        SQLiteDatabase db = getWritableDatabase();


        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1 ORDER BY "+COLUMN_SCORE+" ASC;";


        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_NAME));
                result += colSeprator;
            }

            if (c.getString(c.getColumnIndex(COLUMN_SCORE)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_SCORE));
                result += colSeprator;
            }

            if (c.getString(c.getColumnIndex(COLUMN_DATE)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_DATE));
                result += colSeprator;
            }

            if (c.getString(c.getColumnIndex(COLUMN_TIME)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_TIME));
                result += colSeprator;
            }

            result += rowSeprator;
            c.moveToNext();
           // Log.i("gaurav",result);

        }
        db.close();

        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

}
