package com.dhakasetup.sakib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dhakasetup.sakib.model.Home;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.network.HomeApi;
import com.dhakasetup.sakib.network.RetrofitApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        HomeApi homeApi = RetrofitApiClient.getClient().create(HomeApi.class);
        Call<Home> homeCall = homeApi.getHome();
        homeCall.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                Gson gson = new Gson();
                String responseString = gson.toJson(response.body());
                //Log.d(TAG, "onResponse: "+responseString);
                getSharedPreferences("dhakasetup",MODE_PRIVATE)
                        .edit()
                        .putString("home",responseString)
                        .commit();
                HomeData.putHome(SplashActivity.this);
                /*
                Gson gson = new Gson();
                Gson pretty = new GsonBuilder().setPrettyPrinting().create();
                JsonParser parser = new JsonParser();
                JsonElement je = parser.parse(gson.toJson(response.body()).toString());
                String veryLongString = pretty.toJson(je);
                int maxLogSize = 1000;
                for(int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
                    int start = i * maxLogSize;
                    int end = (i+1) * maxLogSize;
                    end = end > veryLongString.length() ? veryLongString.length() : end;
                    Log.v(TAG, veryLongString.substring(start, end));
                }
                */
                //Log.d(TAG, "onResponse: " + pretty.toJson(je));
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });

        //Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        //startActivity(intent);
    }

}
