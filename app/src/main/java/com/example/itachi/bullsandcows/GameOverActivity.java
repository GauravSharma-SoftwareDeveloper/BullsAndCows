package com.example.itachi.bullsandcows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity implements GameOverBottomFragment.gameOverBottomMethods{

    MyDBTempDataHandler myDBTempDataHandler;

    private static TextView headingView;
    private static TableLayout myTableLayout;
    private static String playerName;
    private static int playerScore;

    private static final char colSeprator = '|';
    private static final char rowSeprator = '$';
    MyDBHighscoreHandler myDBHighscoreHandler;
    MyDBHighscorePlayerHandler myDBHighscorePlayerHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        //headingView=(TextView)findViewById(R.id.headingView);




        /*
        setting the game to be over
         */
        SharedPreferences sp=getSharedPreferences("PLAYER_TEMP_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("NEWGAME",true);
        editor.commit();



        GameTableTempFragment myGameTableTempFragment= new GameTableTempFragment();
        myGameTableTempFragment.changeHeading("Result");

        GameOverTopFragment myGameOverTopFragment =new GameOverTopFragment();





        myTableLayout =myGameTableTempFragment.getTableLayout();




        //Log.i("gaurav","guemmenassai");

        Bundle data =getIntent().getExtras();



        playerName= data.getString("PLAYERNAME","Guest");
        playerScore =data.getInt("TRYSNO",0);

        myGameOverTopFragment.setViews(
                playerName,
                data.getInt("ORIGINALNO",0),
                playerScore
        );


        myDBTempDataHandler = new MyDBTempDataHandler(this, null, null, 1);
        String result = myDBTempDataHandler.databaseToString();


        parseAndAddToTable(myTableLayout,result);

        myDBTempDataHandler.emptyDatabase();
        myDBHighscorePlayerHandler =new MyDBHighscorePlayerHandler(this,null,null,1) ;
        myDBHighscoreHandler =new MyDBHighscoreHandler(this,null,null,1);
        myDBHighscoreHandler.addRow(
                playerName,
                playerScore
        );


        myDBHighscorePlayerHandler.addRow(
                playerName,
                playerScore
        );


    }


    public void parseAndAddToTable(TableLayout myTableLayout,String result) {

        String sNoIntString, guessedNoString, bullsNoString, cowsNoString;

        for (int i = 0, j = 0; i < result.length(); ) {

            while (result.charAt(j) != colSeprator) j++;

            //Log.i("gaurav","i:"+i+"j:"+j);

            sNoIntString = result.substring(i, j);
            i = ++j;

            // Log.i("gaurav","1->"+sNoIntString);


            while (result.charAt(j) != colSeprator) j++;

            //Log.i("gaurav","i:"+i+"j:"+j);

            guessedNoString = result.substring(i, j);
            i = ++j;

            //Log.i("gaurav","2->"+guessedNoString);

            while (result.charAt(j) != colSeprator) j++;

            //Log.i("gaurav","i:"+i+"j:"+j);

            bullsNoString = result.substring(i, j);
            i = ++j;

            //Log.i("gaurav","3->"+bullsNoString);

            while (result.charAt(j) != colSeprator) j++;

            //Log.i("gaurav","i:"+i+"j:"+j);

            cowsNoString = result.substring(i, j);
            i = ++j;

            //Log.i("gaurav","4->"+cowsNoString);

            while (result.charAt(j) != rowSeprator) j++;
            i = ++j;

            addRowData(
                    myTableLayout,
                    Integer.parseInt(sNoIntString),
                    Integer.parseInt(guessedNoString),
                    Integer.parseInt(bullsNoString),
                    Integer.parseInt(cowsNoString)
            );

        }


    }


    //dynamically addding row data in the table
    public void addRowData(TableLayout myTableLayout,int sNoInt, int guessedNo, int bullsNo, int cowsNo) {


        myDBTempDataHandler.addRow(sNoInt, guessedNo, bullsNo, cowsNo);


        TableRow tr = new TableRow(this);

        //Log.i("gaurav","hi");

        TableRow.LayoutParams mySettings = new TableRow.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        );

        mySettings.gravity = Gravity.CENTER;


        tr.setLayoutParams(mySettings);


        Resources r = getResources();
        int pixel60dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60,
                r.getDisplayMetrics()
        );

        int pixel120dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120,
                r.getDisplayMetrics()
        );


        int pixel55dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 55,
                r.getDisplayMetrics()
        );


        //text1.setWidth(pixel);


        TextView newViewSNo = new TextView(this);
        // newViewSNo.setId(1);
        newViewSNo.setWidth(pixel55dp);
        newViewSNo.setText("" + sNoInt);
        newViewSNo.setGravity(Gravity.CENTER);
        newViewSNo.setPadding(5, 5, 5, 0);

        tr.addView(newViewSNo);


        TextView newViewGuessedNo = new TextView(this);
        // newViewSNo.setId(2);
        newViewGuessedNo.setWidth(pixel120dp);
        newViewGuessedNo.setText("" + guessedNo);
        newViewGuessedNo.setGravity(Gravity.CENTER);
        //newViewGuessedNo.setLayoutParams(mySettings);
        newViewGuessedNo.setPadding(5, 5, 5, 0);
        tr.addView(newViewGuessedNo);

        TextView newViewBullsNo = new TextView(this);
        //newViewSNo.setId(3);
        newViewBullsNo.setWidth(pixel55dp);
        newViewBullsNo.setText("" + bullsNo);
        newViewBullsNo.setGravity(Gravity.CENTER);
        newViewBullsNo.setPadding(5, 5, 5, 0);
        tr.addView(newViewBullsNo);


        TextView newViewCowsNo = new TextView(this);
        //newViewSNo.setId(4);
        newViewCowsNo.setText("" + cowsNo);
        newViewCowsNo.setGravity(Gravity.CENTER);
        newViewCowsNo.setWidth(pixel60dp);
        newViewCowsNo.setPadding(5, 5, 5, 0);
        tr.addView(newViewCowsNo);



        myTableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.MATCH_PARENT
        ));


        //Toast.makeText(this,"Hello!!"+tr.toString(),Toast.LENGTH_SHORT).show();
        //myGameTableTempFragment.addRow(tr);

    }


    @Override
    public void onMainMenuButtonClicked(View v) {
        Intent i=new Intent(this,MainActivity.class);
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
    public void onPlayAgainButtonClicked(View v) {


        Intent i=new Intent(this,GameActivity.class);
        i.putExtra("PLAYERNAME",playerName);

        startActivity(i);
        this.finish();
    }
}
