package com.dhakasetup.sakib.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dhakasetup.sakib.MainActivity;
import com.dhakasetup.sakib.R;



public class LoginFragment extends Fragment {


    MainActivity activity;
    EditText numberText;
    Button sendBtn;


    public LoginFragment() {
    }


    public static LoginFragment newInstance(MainActivity activity) {
        LoginFragment fragment = new LoginFragment();
        fragment.activity = activity;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        Toolbar toolbar = view.findViewById(R.id.appbar_profile);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_notifications_black_32dp);
        activity.setTitle("Dhaka Setup");

        numberText = view.findViewById(R.id.numberET);
        sendBtn = view.findViewById(R.id.sendCodeBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+88" + numberText.getText().toString();
                sendCode(number);
            }
        });

        return view;
    }

    private void sendCode(String number) {
        OtpFragment otpFragment = OtpFragment.newInstance(activity,number);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, otpFragment)
                .commit();
    }






}
