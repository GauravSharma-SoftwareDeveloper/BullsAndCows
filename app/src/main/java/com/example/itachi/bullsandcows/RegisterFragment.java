package com.example.itachi.bullsandcows;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AndroidException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterFragment extends Fragment {

    private static EditText nameText;
    private static EditText usernameText;
    private static EditText passwordText;
    private static Button registerSummitButton;

    public interface RegisterMethods {
       // public void onLoginButtonClicked(View v);
        public void onRegisterSummitButtonClicked(View v,String name,String username,String password);
    }

    RegisterMethods myActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            /** casting provided activity into MenuFragmentMethods interface to ensure methods **/
            myActivity = (RegisterMethods) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_register,container,false);

        nameText=(EditText)view.findViewById(R.id.nameTextNew);


        usernameText=(EditText)view.findViewById(R.id.usernameText);
        passwordText=(EditText)view.findViewById(R.id.passwordText);
        registerSummitButton=(Button) view.findViewById(R.id.registerSummitButton);

        registerSummitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myActivity.onRegisterSummitButtonClicked(view,
                        nameText.getText().toString(),
                        usernameText.getText().toString(),
                        passwordText.getText().toString()
                );
            }
        });





        return view;


    }



    public void initialiseViews(){
        nameText.setText("");
        usernameText.setText("");
        passwordText.setText("");
    }


}
