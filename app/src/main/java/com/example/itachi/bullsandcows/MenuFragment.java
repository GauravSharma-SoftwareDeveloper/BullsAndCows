package com.example.itachi.bullsandcows;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MenuFragment extends Fragment {

    private static Button playButton;
    private static Button highScoreButton;
    private static Button preferencesButton;
    private static Button login_signupButton;
    private static Button logoutMainMenuButton;
    private static Button quitButton;

    /** inteface ensuring these methods are present in the activity including this fragment  **/
    public interface MenuFragmentMethods {
        public void onPlayButtonClicked(View v);
        public void onHighscoreButtonClicked(View v);
        public void onPreferencesButtonClicked(View v);
        public void onLogin_SignupButtonClicked(View v);
        public void onLogoutMainMenuButtonClicked(View v);
        public void onQuitButtonClicked(View v);

    }

    MenuFragmentMethods myActivity ;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            /** casting provided activity into MenuFragmentMethods interface to ensure methods **/
            myActivity = (MenuFragmentMethods) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        /***  Getting references to buttons and their  listeners  **/

        playButton =(Button) view.findViewById(R.id.playButton);
        highScoreButton=(Button)view.findViewById(R.id.highScoreButton);
        preferencesButton=(Button)view.findViewById(R.id.preferencesButton);
        login_signupButton=(Button) view.findViewById(R.id.login_signupButton);
        logoutMainMenuButton=(Button) view.findViewById(R.id.logoutMainMenuButton);
        quitButton=(Button) view.findViewById(R.id.quitButton);


        playButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                myActivity.onPlayButtonClicked(view);
            }
        });

        highScoreButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                myActivity.onHighscoreButtonClicked(view);
            }
        });

        preferencesButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                myActivity.onPreferencesButtonClicked(view);
            }
        });

        login_signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myActivity.onLogin_SignupButtonClicked(view);
            }
        });

        logoutMainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myActivity.onLogoutMainMenuButtonClicked(view);
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myActivity.onQuitButtonClicked(view);
            }
        });

        /***  Getting references to buttons and their  listeners  **/




        return view;
    }
}
