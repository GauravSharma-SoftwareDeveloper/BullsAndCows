package com.example.itachi.bullsandcows;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by itachi on 23/5/16.
 */
public class MyDBLoginRegisterHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="bullsandcowsplayerinfo.db";
    private static final String TABLE_NAME="playerstatistics";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_USERNAME="username";
    private static final String COLUMN_PASSWORD="password";
    private static final String COLUMN_SCORE="score";




    public MyDBLoginRegisterHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE "+ TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_NAME	+ " TEXT, " +
                COLUMN_USERNAME	+ " TEXT UNIQUE, " +
                COLUMN_PASSWORD	+ " TEXT, " +
                COLUMN_SCORE	+ " INTEGER " +
                ");";

        //String query="CREATE TABLE prod(_id INTEGER AUTOINCREMENT, productname TEXT);";
        db.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }


    public boolean registerPlayer(PlayerInfo player){

        SQLiteDatabase db = getWritableDatabase();
        String query= "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_USERNAME+" =\""+player.get_username()+"\";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(!c.isAfterLast()){
            return false;
        }


        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, player.get_name());
        values.put(COLUMN_USERNAME,player.get_username());
        values.put(COLUMN_PASSWORD,player.get_password());
        values.put(COLUMN_SCORE,player.get_score());

        db.insert(TABLE_NAME, null, values);
        db.close();

        return true;
    }


        /*** Returns null if not found****/
    public String validateLoginReturnName(String username ,String password){
        String result = "";
        SQLiteDatabase db = getWritableDatabase();
        String query= "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_USERNAME+" =\""+username+"\";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(!c.isAfterLast()){

            //Toast.makeText(this," ",Toast.LENGTH_SHORT).show();
            //Log.i("gaurav"," 1:"+password +"  2:"+c.getString(c.getColumnIndex(COLUMN_PASSWORD))+"");

                if(password.equals(c.getString(c.getColumnIndex(COLUMN_PASSWORD)))){
                    result=c.getString(c.getColumnIndex(COLUMN_NAME));
                    //Log.i("gaurav",result);
                    db.close();
                }else{
                    return null;
                }

        }else{
            return null;
        }
       // Log.i("gaurav",result);
        return result;

    }



    public String databaseToString() {
        String result = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1 ";


        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                result += c.getString(c.getColumnIndex(COLUMN_NAME));
                result += "   ";
                }

                if (c.getString(c.getColumnIndex(COLUMN_SCORE)) != null) {
                    result += c.getString(c.getColumnIndex(COLUMN_SCORE));

                }
                    //Log.i("Gaurav1",result);

                result += '\n';
                c.moveToNext();
            }
            db.close();
            return result;

        }


}
