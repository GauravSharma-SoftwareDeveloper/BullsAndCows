package com.example.itachi.bullsandcows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TempTryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_try);



        TableLayout t1=(TableLayout)findViewById(R.id.tempTryTableLayout);
        TableRow tr=new TableRow(this);

        Log.i("gaurav","hi");

        tr.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        ));


        TextView newViewSNo =new TextView(this);
      //  newViewSNo.setId(1);
        newViewSNo.setText(""+2);
        newViewSNo.setPadding(5,5,5,5);

        tr.addView(newViewSNo);


        TextView newViewGuessedNo =new TextView(this);
        //newViewSNo.setId(2);
        newViewGuessedNo.setText(""+6);
        newViewGuessedNo.setPadding(5,5,5,5);
        tr.addView(newViewGuessedNo);

        TextView newViewBullsNo =new TextView(this);
        //newViewSNo.setId(3);
        newViewBullsNo.setText(""+4);
        newViewBullsNo.setPadding(5,5,5,5);
        tr.addView(newViewBullsNo);


        TextView newViewCowsNo =new TextView(this);
       // newViewSNo.setId(4);
        newViewCowsNo.setText(""+90);
        newViewCowsNo.setPadding(5,5,5,5);
        tr.addView(newViewCowsNo);

        t1.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.MATCH_PARENT
        ));



    }
}
