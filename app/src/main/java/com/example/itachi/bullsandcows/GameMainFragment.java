package com.example.itachi.bullsandcows;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameMainFragment extends Fragment {

    private static EditText inputText;
    private static TextView bullsViewNo, cowsViewNo, scoreViewNo;
    private static Button checkButton;

    public interface GameMainFragmentMethods {
        public void onCheckButtonClicked(View v);

    }

    GameMainFragmentMethods myActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            myActivity = (GameMainFragmentMethods) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_main, container, false);

        inputText = (EditText) view.findViewById(R.id.inputText);
        bullsViewNo = (TextView) view.findViewById(R.id.bullsViewNo);
        cowsViewNo = (TextView) view.findViewById(R.id.cowsViewNo);
        scoreViewNo = (TextView) view.findViewById(R.id.scoreViewNo);
        checkButton = (Button) view.findViewById(R.id.checkButton);


        // Inflate the layout for this fragment
        //

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myActivity.onCheckButtonClicked(view);
            }
        });

        return view;

    }

    public String getInputNumber() {
        return inputText.getText().toString();

    }

    public void initialiseInputNumber(){
        inputText.setText("");
        //inputText.setHint("");
    }


    public void setCheckButtonEnabled(boolean b) {

        //Log.i("gaurav",checkButton.getText().toString());
        checkButton.setEnabled(b);

    }


    public int getBulls(){
        return Integer.parseInt(bullsViewNo.getText().toString());
    }

    public int getCows(){

        return Integer.parseInt(cowsViewNo.getText().toString());

    }

    public int getGuessedNo(){
        try{
            return Integer.parseInt(inputText.getText().toString());
        }catch(Exception e){
            return 0;
        }
    }

    public int getScore(){
        return Integer.parseInt(scoreViewNo.getText().toString());
    }


    public void setResults(int bullsNo, int cowsNo, int scoreNo,int guessedNo) {
        bullsViewNo.setText("" + bullsNo);
        cowsViewNo.setText("" + cowsNo);
        scoreViewNo.setText("" + scoreNo);
        inputText.setHint(""+ guessedNo);
    }

}
