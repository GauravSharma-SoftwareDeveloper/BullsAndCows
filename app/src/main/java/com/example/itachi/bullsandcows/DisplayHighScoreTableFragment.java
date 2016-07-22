package com.example.itachi.bullsandcows;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayHighScoreTableFragment extends Fragment {


    private static final char colSeprator = '|';
    private static final char rowSeprator = '$';

    private static TableLayout myTableLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_display_high_score_table, container, false);

        myTableLayout =(TableLayout)view.findViewById(R.id.tableEntrysTable);
        return view;
    }

    public TableLayout getTableLayout(){
        return myTableLayout;
    }
}
