package com.example.itachi.bullsandcows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements MenuFragment.MenuFragmentMethods{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onLogoutMainMenuButtonClicked(View v) {
        SharedPreferences sp = getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("IS_LOGGED_IN", false);
        editor.putString("USERNAME", "");
        editor.putString("PASSWORD", "");
        editor.putString("PLAYERNAME", "");
        editor.commit();
    }

    @Override
    public void onQuitButtonClicked(View v) {
        this.finish();
    }

    @Override
    public void onPlayButtonClicked(View v) {

        // Log.i("gaurav","o hayo Gujaimasta!!");

        SharedPreferences sp=getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE);
        boolean isLoggedIn=sp.getBoolean("IS_LOGGED_IN",false);
        if(isLoggedIn){
            Intent i=new Intent(this,GameActivity.class);
            i.putExtra("PLAYERNAME",sp.getString("PLAYERNAME","Guest"));
            startActivity(i);
            this.finish();
        }else {
            Toast.makeText(this,"Please Login!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, LoginRegisterActivity.class);
            startActivity(i);
            this.finish();
        }
    }

    @Override
    public void onLogin_SignupButtonClicked(View v) {
        Intent i=new Intent(this,LoginRegisterActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onHighscoreButtonClicked(View v) {
        Intent i=new Intent(this,DisplayHighScoreActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onPreferencesButtonClicked(View v) {

        Toast.makeText(this,"Wait For a Sec !!",Toast.LENGTH_SHORT);

    }
}
