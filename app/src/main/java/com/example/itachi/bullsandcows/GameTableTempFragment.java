package com.example.itachi.bullsandcows;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameTableTempFragment extends Fragment {



    //TableRow tr;
    private static TextView headingView;
    private static TableLayout myTableLayout;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ///TableLayout mylayout =new TableLayout(myActivity);

        View view = inflater.inflate(R.layout.fragment_game_table_temp, container, false);
        headingView =(TextView) view.findViewById(R.id.headingView);
        myTableLayout =(TableLayout)view.findViewById(R.id.gameTableLayout);



        return view;
    }

    public void changeHeading(String heading){
        headingView.setText(""+heading);
    }

    public TableLayout getTableLayout(){
        return myTableLayout;
    }


}
