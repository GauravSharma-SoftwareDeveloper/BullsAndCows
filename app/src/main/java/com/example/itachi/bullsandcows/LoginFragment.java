package com.example.itachi.bullsandcows;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class LoginFragment extends Fragment {

    private static EditText usernameText;
    private static EditText passwordText;
    private static Button loginSummitButton;


    public interface LoginMethods {
        public void onLoginSummitButtonClicked(View v,String username,String password);
        //public void onRegisterButtonClicked(View v);
    }

    LoginMethods myActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            /** casting provided activity into MenuFragmentMethods interface to ensure methods **/
            myActivity = (LoginMethods) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_login,container,false);

        usernameText=(EditText)view.findViewById(R.id.usernameText);
        Log.i("gaurav","poo "+usernameText+"");
        passwordText=(EditText)view.findViewById(R.id.passwordText);
        loginSummitButton=(Button) view.findViewById(R.id.loginSummitButton);




        loginSummitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myActivity.onLoginSummitButtonClicked(view,
                        usernameText.getText().toString(),
                        passwordText.getText().toString()
                );
            }
        });



        return view;


    }

    public void setViews(String username,String password){
        Log.i("gaurav","12"+usernameText.getText().toString());
       // usernameText.setText(""+username);
        passwordText.setText(password);
    }

}
