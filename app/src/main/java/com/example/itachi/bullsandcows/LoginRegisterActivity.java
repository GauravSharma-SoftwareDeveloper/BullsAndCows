package com.example.itachi.bullsandcows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginRegisterActivity extends FragmentActivity implements LoginFragment.LoginMethods,
        RegisterFragment.RegisterMethods {


    private static Button loginButton;
    private static Button registerButton;

    MyDBLoginRegisterHandler myDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);


        myDBHandler = new MyDBLoginRegisterHandler(this, null, null, 1);

        View v = null;//=new View(this,null,1,1);
        onLoginButtonClicked(v);

    }


    public void onLoginButtonClicked(View v) {
/*
        SharedPreferences sp = getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE);
        String name=sp.getString("PLAYERNAME","Guest"),username=sp.getString("USERNAME","Guest"),
                password=sp.getString("PASSWORD","Guest");
        boolean isLoggedIn=sp.getBoolean("IS_LOGGED_IN",false);
*/


        loginButton.setEnabled(false);
        registerButton.setEnabled(true);


        LoginFragment f1 = new LoginFragment();




        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft;
        Fragment f = (Fragment) fm.findFragmentById(R.id.loginRegisterFrameLayout);
        if (f == null) {

            ft = fm.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.add(R.id.loginRegisterFrameLayout, f1).commit();


        } else if (f instanceof RegisterFragment) {


            ft = fm.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.loginRegisterFrameLayout, f1).commit();
        }



    }

    public void onRegisterButtonClicked(View v) {


        loginButton.setEnabled(true);

        registerButton.setEnabled(false);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft;
        Fragment f = fm.findFragmentById(R.id.loginRegisterFrameLayout);

        if (f == null) {

            f = new RegisterFragment();
            ft = fm.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.add(R.id.loginRegisterFrameLayout, f).commit();

        } else if (f instanceof LoginFragment) {


            Fragment f1 = new RegisterFragment();
            ft = fm.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            //ft.add(R.id.loginRegisterFrameLayout,f).commit();
            ft.replace(R.id.loginRegisterFrameLayout, f1).commit();


        }


    }


    @Override
    public void onLoginSummitButtonClicked(View v, String username, String password) {


        String name = myDBHandler.validateLoginReturnName(username, password);
        if (name == null) {
            Toast.makeText(this, "Incorrect Details", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Welcome: " + name + " !!", Toast.LENGTH_SHORT).show();

            SharedPreferences sp = getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("IS_LOGGED_IN", true);
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
            editor.putString("PLAYERNAME", name);
            editor.commit();

            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("PLAYERNAME", name);
            startActivity(i);
            this.finish();

        }

    }

    @Override
    public void onRegisterSummitButtonClicked(View v, String name, String username, String password) {

        PlayerInfo player = new PlayerInfo(name, username, password);
        boolean result = myDBHandler.registerPlayer(player);
        if (result) {
            SharedPreferences sp = getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("IS_LOGGED_IN", true);
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
            editor.putString("PLAYERNAME", name);
            editor.commit();

            Toast.makeText(this, "Registered!!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("PLAYERNAME", name);
            startActivity(i);
            this.finish();

        } else {
            Toast.makeText(this, "username already taken !!", Toast.LENGTH_SHORT).show();
            RegisterFragment rr = new RegisterFragment();
            rr.initialiseViews();
        }


    }



}
