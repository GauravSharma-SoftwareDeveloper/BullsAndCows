package com.example.itachi.bullsandcows;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameOverBottomFragment extends Fragment {


    private static Button mainMenuButton,playAgainButton,highscoresButon;

    public GameOverBottomFragment() {
        // Required empty public constructor
    }

    public interface gameOverBottomMethods{
        public void onMainMenuButtonClicked(View v);
        public void onHighscoreButtonClicked(View v);
        public void onPlayAgainButtonClicked(View v);

    }

    gameOverBottomMethods myActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            /** casting provided activity into MenuFragmentMethods interface to ensure methods **/
            myActivity = (gameOverBottomMethods) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_game_over_bottom, container, false);
        mainMenuButton=(Button)view.findViewById(R.id.mainMenuGameOverBottomButton);
        playAgainButton=(Button)view.findViewById(R.id.playAgainGameOverBottomButton);
        highscoresButon=(Button)view.findViewById(R.id.highscoreGameOverBottomButton) ;


        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myActivity.onMainMenuButtonClicked(view);
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myActivity.onPlayAgainButtonClicked(view);
            }
        });

        highscoresButon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myActivity.onHighscoreButtonClicked(view);
            }
        });

        return view;
    }

}
