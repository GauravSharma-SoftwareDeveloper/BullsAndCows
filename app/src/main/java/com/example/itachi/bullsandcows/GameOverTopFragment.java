package com.example.itachi.bullsandcows;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameOverTopFragment extends Fragment {

    private static TextView originalViewNo ,trysViewNo,playerNameView;

    public GameOverTopFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_game_over_top, container, false);

        originalViewNo =(TextView)view.findViewById(R.id.originalViewNo);
        trysViewNo=(TextView) view.findViewById(R.id.trysViewNo);
        playerNameView=(TextView) view.findViewById(R.id.playerNameView);


        return view;
    }

    public void setViews(String playerName,int originalNo,int trysNo){
        originalViewNo.setText(""+originalNo);
        trysViewNo.setText(""+trysNo);
        playerNameView.setText(""+playerName+"'s Game Analysis");
    }
}
