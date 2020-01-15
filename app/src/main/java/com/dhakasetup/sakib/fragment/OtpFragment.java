package com.dhakasetup.sakib.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dhakasetup.sakib.MainActivity;
import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.model.User;
import com.dhakasetup.sakib.network.HomeApi;
import com.dhakasetup.sakib.network.RetrofitApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtpFragment extends Fragment {

    MainActivity activity;
    String number;
    EditText otpEt;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    FirebaseAuth mAuth;
    Button verifyBtn;
    ProgressBar loader;

    public OtpFragment() {
        // Required empty public constructor
    }

    public static OtpFragment newInstance(MainActivity activity,String num) {
        OtpFragment fragment = new OtpFragment();
        fragment.activity = activity;
        fragment.number = num;
        fragment.mAuth = FirebaseAuth.getInstance();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_otp, container, false);
        Toolbar toolbar = view.findViewById(R.id.appbar_profile);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_notifications_black_32dp);
        activity.setTitle("Dhaka Setup");

        loader = view.findViewById(R.id.otpLoader);
        otpEt = view.findViewById(R.id.otpEt);
        verifyBtn = view.findViewById(R.id.otpVerifyBtn);
        TextView numTxt = view.findViewById(R.id.numberOtpTv);
        numTxt.setText(number);
        sendCode(number);

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(otpEt.getText().toString());
            }
        });

        return view;
    }

    private void sendCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,
                60,
                TimeUnit.SECONDS,
                activity,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            //Getting the code sent by SMS
            String code = credential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
//                Toast.makeText(activity,"onVerificationCompleted "+code,Toast.LENGTH_LONG).show();
                loader.setVisibility(View.INVISIBLE);
                otpEt.setText(code);
                //verifying the code
                //verifyCode(code);
            } else {
                signInWithPhoneAuthCredential(credential);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.w("JEJE", "onVerificationFailed", e);
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            Toast.makeText(activity,"onCodeSent "+verificationId,Toast.LENGTH_SHORT).show();
            mVerificationId = verificationId;
            mResendToken = forceResendingToken;
        }
    };

    private void verifyCode(String otp) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loader.setVisibility(View.VISIBLE);
                            //verification successful we will start the profile activity
//                            Toast.makeText(activity,"task.isSuccessful",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(VerifyPhoneActivity.this, ProfileActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
                            getToken();

                        } else {

                            //verification unsuccessful.. display an error message
                            Log.w("JEJE", "onVerificationFailed", task.getException());

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }
                            Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void getToken(){
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( activity,
            new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    String newToken = instanceIdResult.getToken();
                    Log.e("newToken", newToken);
                    loginUser(newToken);
                }
        });
    }

    private void loginUser(String token){
        HomeApi homeApi = RetrofitApiClient.getClient().create(HomeApi.class);
        User user = new User();
        user.setUid(mAuth.getCurrentUser().getUid());
        user.setMobile(mAuth.getCurrentUser().getPhoneNumber());
        user.setToken(token);
        Log.e("JEJE", user.getUid());
        Call<User> userCall = homeApi.loginUser(user);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
                loader.setVisibility(View.INVISIBLE);
                Toast.makeText(activity,userResponse.getMobile(),Toast.LENGTH_SHORT).show();
                activity.loadHome();
//                otpEt.setText(userResponse.getUid());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.getCause().printStackTrace();
            }
        });
    }

}
