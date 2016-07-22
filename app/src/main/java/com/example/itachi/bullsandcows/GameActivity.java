package com.example.itachi.bullsandcows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements GameMainFragment.GameMainFragmentMethods {


    private static final char colSeprator = '|';
    private static final char rowSeprator = '$';
    private static String playerName = "";
    private static TextView topWelcomeView;
    private static int noOfDigits = 4;
    private static int originalNumber[] = new int[noOfDigits];
    private static int originalNumberNo = 0;
    private static int userEnteredNumber[] = new int[200];
    private static int userEnteredNumberCows[] = new int[200];
    private static int count = 0;
    GameMainFragment myGameMainFragment = new GameMainFragment();
    GameTableTempFragment myGameTableTempFragment = new GameTableTempFragment();
    MyDBTempDataHandler myDBTempDataHandler;
    int inputNumber = 0;
    private int sNoInt = 0;
    private int sum = 0;

    /**
     * snddjjdfjfcf
     *
     * @param savedInstanceState
     */


    TableLayout myTableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();
        playerName = bundle.getString("PLAYERNAME", "Guest");

        topWelcomeView = (TextView) findViewById(R.id.topWelcomeView);
        topWelcomeView.setText("Welcome "+playerName);

        GameTableTempFragment myGameTableTempFragment=new GameTableTempFragment();

         myTableLayout = myGameTableTempFragment.getTableLayout();

        // Log.i("gaurav", "1");

        myDBTempDataHandler = new MyDBTempDataHandler(this, null, null, 1);

        //Log.i("gaurav", "2");


        String alreadyPlayingPlayerName = "";


        SharedPreferences sp = getSharedPreferences("PLAYER_TEMP_DATA", Context.MODE_PRIVATE);
        alreadyPlayingPlayerName = sp.getString("PLAYERNAME", "");
        int prevOriginalNo = sp.getInt("ORIGINALNO", 0);
        int prevBulls = sp.getInt("BULLS", 0);
        int prevSNoInt = sp.getInt("SNO", 0);
        int prevCows = sp.getInt("COWS", 0);
        int prevCount = sp.getInt("TRYS", 0);
        int prevGuessedNo = sp.getInt("GUESSEDNO", 0);
        boolean newGame =sp.getBoolean("NEWGAME",true);

        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("NEWGAME",false);
        editor.commit();

        //Log.i("gaurav","O hayo");
        //Log.i("gaurav","-->"+myDBTempDataHandler.getPlayerName());
        //SQLiteDatabase db;
        //myDBTempDataHandler.onCreate(db);

        //Log.

        if (playerName.equals(alreadyPlayingPlayerName) && !newGame) {
            //load prev data
            sNoInt = prevSNoInt;
            count = prevCount;
            originalNumberNo = prevOriginalNo;
            String result = myDBTempDataHandler.databaseToString();


            String x = "" + prevOriginalNo;
            for (int i = 0; i < x.length(); ++i) {
                originalNumber[i] = Integer.parseInt("" + x.charAt(i));
                // Log.i("gaurav","i:"+i+" o:"+ originalNumber[i]+" C:"+x.charAt(i));
            }


            myGameMainFragment.setResults(prevBulls, prevCows, prevCount, prevGuessedNo);

            parseAndAddToTable(myTableLayout,result);

            //Log.i("gaurav","1.2");
        } else {


            for (int i = 0; i < noOfDigits; ++i) {
                Random r = new Random();
                originalNumber[i] = r.nextInt(9) + 1;   //random no b/w 1-9
            }

            sum = 0;
            for (int i = 0; i < noOfDigits; ++i) {
                sum = sum * 10 + originalNumber[i];
            }
            originalNumberNo = sum;
            count = 0;
            sNoInt = 0;
            myGameMainFragment.setResults(0, 0, 0, 0);
            //delete prev data
            myDBTempDataHandler.emptyDatabase();

            // Log.i("gaurav","1.3");
        }


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
    public void onCheckButtonClicked(View v) {


        //Log.i("gaurav","O hayota");


        // Random r=new Random();
        //originalNumber=r.nextInt(10);

        //Toast.makeText(this,"hi: "+originalNumber,Toast.LENGTH_SHORT).show();


        String inputNumberString = myGameMainFragment.getInputNumber();


        /****
         *
         * cheat code to get the number
         */
        //Toast.makeText(this, "" + inputNumberString, Toast.LENGTH_SHORT).show();
        if (inputNumberString.equals("70547")) {
            Toast.makeText(this, "original number :" + originalNumberNo, Toast.LENGTH_SHORT).show();
        } else {

            try {
                inputNumber = Integer.parseInt(inputNumberString);

            } catch (NumberFormatException e) {

                inputNumber = -1;
                Toast.makeText(this, "Please enter a valid number!!", Toast.LENGTH_SHORT).show();

            }


            /****
             *
             boolean isNotNumber=false;
             for(int i=0;i<inputNumberString.length();++i){
             String a=""+inputNumberString.charAt(i);
             String x="0123456789";
             if( !x.contains(a) ){
             Log.i("gaurav","x:"+x+" a:"+a+"  "+isNotNumber);
             isNotNumber=true;
             break;
             }
             }
             ***/

            if (inputNumber != -1) {
                inputNumber = Integer.parseInt(inputNumberString);
                int inputNumberLength = 0;
                int x = inputNumber, y = noOfDigits - 1;

                while (x > 0 && y >= 0) {
                    userEnteredNumber[y] = x % 10;
                    inputNumberLength++;
                    x /= 10;
                    y--;
                }
                if (inputNumberLength != noOfDigits || x > 0 || y > 0) {

                    Toast.makeText(this, "Enter no of appropriate length!!", Toast.LENGTH_SHORT).show();
                } else {
                    count++;
                    int noOfBulls = 0, i = 0, j = 0;

                    while (i < noOfDigits) {
                        if (originalNumber[i] == userEnteredNumber[i]) noOfBulls++;
                        i++;
                    }

                    int noOfCows = 0;

                    for (i = 0; i < noOfDigits; ++i) {
                        userEnteredNumberCows[i] = 0;
                    }

                    for (i = 0; i < noOfDigits; ++i) {
                        for (j = 0; j < noOfDigits; ++j) {
                            if (originalNumber[i] == userEnteredNumber[j] && userEnteredNumberCows[j] == 0) {
                                noOfCows++;
                                userEnteredNumberCows[j] = 1;
                                break;
                            }
                        }
                    }

                    noOfCows -= noOfBulls;


                    int newScore, prevScore;
                    prevScore = myGameMainFragment.getScore();
                    newScore = generateScore(noOfBulls, noOfCows, prevScore, count);

                    myGameMainFragment.setResults(noOfBulls, noOfCows, newScore, inputNumber);

                    ++sNoInt;
                    //Log.i("gaurav",""+sNoInt+" "+inputNumber+" "+noOfBulls+" "+noOfCows);
                    addRowData(myTableLayout,sNoInt, inputNumber, noOfBulls, noOfCows);

                    if (noOfBulls == noOfDigits) {
                        Toast.makeText(this, "You Win!!", Toast.LENGTH_SHORT).show();
                        myGameMainFragment.setCheckButtonEnabled(false);

                        Intent intent=new Intent(this,GameOverActivity.class);


                        intent.putExtra("PLAYERNAME",playerName);
                        intent.putExtra("ORIGINALNO",originalNumberNo);
                        intent.putExtra("TRYSNO",count);
                        startActivity(intent);
                        this.finish();
                    }

                }

                // if(inputNumb)

            }

            //myGameMainFragment.setResults(x,x,x);
            //addRowData(x,x,x);

        }


        myGameMainFragment.initialiseInputNumber();

    }


    public int generateScore(int bull, int cow, int prevScore, int count) {
        return count;
    }

    public void onLogoutButtonClicked(View v){

        SharedPreferences sp = getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("IS_LOGGED_IN", false);
        editor.putString("USERNAME", "");
        editor.putString("PASSWORD", "");
        editor.putString("PLAYERNAME", "");
        editor.commit();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sp = getSharedPreferences("PLAYER_TEMP_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("PLAYERNAME", playerName);
        editor.putInt("ORIGINALNO", originalNumberNo);
        editor.putInt("SNO", sNoInt);
        editor.putInt("TRYS", count);
        editor.putInt("BULLS", myGameMainFragment.getBulls());
        editor.putInt("COWS", myGameMainFragment.getCows());
        editor.putInt("GUESSEDNO", myGameMainFragment.getGuessedNo());
        editor.apply();


        super.onDestroy();
    }
}
