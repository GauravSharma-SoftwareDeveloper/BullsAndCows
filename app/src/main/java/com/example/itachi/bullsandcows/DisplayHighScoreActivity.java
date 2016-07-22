package com.example.itachi.bullsandcows;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayHighScoreActivity extends AppCompatActivity {


    //private int sNoInt=0;
    private static final char colSeprator = '|';
    private static final char rowSeprator = '$';




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_high_score);


        View v=null;
        onHighScoreButtonClicked(v);


    }

    public void parseAndAddToTable(TableLayout myTableLayout, String result) {

        String sNoIntString, nameString, dateString, timeString,scoreString;

        int sNoInt=0;
        for (int i = 0, j = 0; i < result.length(); ) {

            sNoIntString=++sNoInt+"";

            while (result.charAt(j) != colSeprator) j++;

            //Log.i("gaurav","i:"+i+"j:"+j);

            nameString = result.substring(i, j);
            i = ++j;

            while (result.charAt(j) != colSeprator) j++;
            scoreString = result.substring(i, j);
            i = ++j;

            //Log.i("gaurav","2->"+guessedNoString);

            while (result.charAt(j) != colSeprator) j++;

            //Log.i("gaurav","i:"+i+"j:"+j);

            dateString = result.substring(i, j);
            i = ++j;

            //Log.i("gaurav","3->"+bullsNoString);

            while (result.charAt(j) != colSeprator) j++;

            //Log.i("gaurav","i:"+i+"j:"+j);

            timeString = result.substring(i, j);
            i = ++j;

            //Log.i("gaurav","4->"+cowsNoString);

            while (result.charAt(j) != rowSeprator) j++;
            i = ++j;

            addRowData(
                    myTableLayout,
                    Integer.parseInt(sNoIntString),
                    nameString,
                    Integer.parseInt(scoreString),
                    dateString,timeString
            );

        }


    }


    //dynamically addding row data in the table
    public void addRowData(TableLayout myTableLayout,int sNoInt, String name,int score,String date,String time) {


        //myDBHighscoreHandler.addRow(sNoInt, guessedNo, bullsNo, cowsNo);


        TableRow tr = new TableRow(this);

        //Log.i("gaurav","hi");

        TableRow.LayoutParams mySettings = new TableRow.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        );

        mySettings.gravity = Gravity.CENTER;


        tr.setLayoutParams(mySettings);


        Resources r = getResources();
        int pixel45dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45,
                r.getDisplayMetrics()
        );

        int pixel50dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                r.getDisplayMetrics()
        );


        int pixel70dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70,
                r.getDisplayMetrics()
        );


        //text1.setWidth(pixel);

        DisplayMetrics metrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //Log.i("gaurav",metrics.widthPixels+"");

        TextView newViewSNo = new TextView(this);
        // newViewSNo.setId(1);
        newViewSNo.setWidth(pixel45dp);
        newViewSNo.setText("" + sNoInt);
        newViewSNo.setGravity(Gravity.CENTER);
        newViewSNo.setPadding(5, 5, 5, 0);

        tr.addView(newViewSNo);


        TextView newViewName = new TextView(this);
        // newViewSNo.setId(2);
        newViewName.setWidth(pixel70dp);
        newViewName.setText("" + name);
        newViewName.setGravity(Gravity.CENTER);
        //newViewGuessedNo.setLayoutParams(mySettings);
        newViewName.setPadding(5, 5, 5, 0);
        tr.addView(newViewName);

        TextView newViewScore = new TextView(this);
        //newViewSNo.setId(3);
        newViewScore.setWidth(pixel70dp);
        newViewScore.setText("" + score);
        newViewScore.setGravity(Gravity.CENTER);
        newViewScore.setPadding(5, 5, 5, 0);
        tr.addView(newViewScore);


        TextView newViewDate = new TextView(this);
        //newViewSNo.setId(4);
        newViewDate.setText("" + date);
        newViewDate.setGravity(Gravity.CENTER);
        newViewDate.setWidth(pixel70dp);
        newViewDate.setPadding(5, 5, 5, 0);
        tr.addView(newViewDate);

        TextView newViewTime = new TextView(this);
        //newViewSNo.setId(4);
        newViewTime.setText("" + time);
        newViewTime.setGravity(Gravity.CENTER);
        newViewTime.setWidth(pixel70dp);
        newViewTime.setPadding(5, 5, 5, 0);
        tr.addView(newViewTime);



        myTableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.MATCH_PARENT
        ));


        //Toast.makeText(this,"Hello!!"+tr.toString(),Toast.LENGTH_SHORT).show();
        //myGameTableTempFragment.addRow(tr);

    }


    public void onHighScoreButtonClicked(View v){


        DisplayHighScoreTableFragment myDisplayHighScoreTableFragment=new DisplayHighScoreTableFragment();
        TableLayout myTableLayout = myDisplayHighScoreTableFragment.getTableLayout();

        myTableLayout.removeAllViews();




        MyDBHighscoreHandler myDBHighscoreHandler=new MyDBHighscoreHandler(this,null,null,1);

        String result =myDBHighscoreHandler.databaseToString();

       /*
        for(int i=0,j=myTableLayout.getChildCount();i<j;++i){
            myTableLayout.removeViewAt(i);
        }
        */

        parseAndAddToTable(
                myTableLayout ,
                result
                );

    }


    public void onPlayerHighScoreButtonClicked(View v){


        DisplayHighScoreTableFragment myDisplayHighScoreTableFragment=new DisplayHighScoreTableFragment();
        TableLayout myTableLayout = myDisplayHighScoreTableFragment.getTableLayout();




        MyDBHighscorePlayerHandler myDBHighscorePlayerHandler=new MyDBHighscorePlayerHandler(this,null,null,1);
        String result =myDBHighscorePlayerHandler.databaseToString();

        myTableLayout.removeAllViews();


        /*
        for(int i=1,j=myTableLayout.getChildCount();i<j;++i){
            //TableRow row=getChildAT(i);
            //myTableLayout.removeViewAt(i);
        }
        */

        parseAndAddToTable(
                myTableLayout,
                result
        );
    }

    public void onMenuButtonClicked(View v){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);

        this.finish();
    }
}
