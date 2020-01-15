package com.dhakasetup.sakib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TimingLogger;
import android.view.MenuItem;
import android.widget.Toast;

import com.dhakasetup.sakib.fragment.HomeFragment;
import com.dhakasetup.sakib.fragment.LoginFragment;
import com.dhakasetup.sakib.fragment.ProfileFragment;
import com.dhakasetup.sakib.model.Home;
import com.dhakasetup.sakib.model.HomeData;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    ProfileFragment profileFragment;
    LoginFragment loginFragment;

    public static int REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        profileFragment = ProfileFragment.newInstance(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,homeFragment,"home")
                .addToBackStack(homeFragment.getClass().getSimpleName())
                .commit();

        HomeData.startTime = System.currentTimeMillis();
        Log.i("logger", "MainActivity start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        Log.i("logger", "FCM Token" +  token);
                    }
                });
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                String fragmentTag = null;
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        HomeData.startTime = System.currentTimeMillis();
                        //selectedFragment = homeFragment;
                        //getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                        //getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new HomeFragment())
                                .commit();
                        fragmentTag = "home";
                        break;
                    case R.id.nav_personal:
                        //selectedFragment = new ProfileFragment();
                        if (FirebaseAuth.getInstance().getCurrentUser() != null){
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, ProfileFragment.newInstance(MainActivity.this))
                                    .commit();
                        } else {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, LoginFragment.newInstance(MainActivity.this))
                                    .commit();
                        }

//                        loginFragment = LoginFragment.newInstance(MainActivity.this);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .add(R.id.fragment_container,loginFragment,"login")
//                                .addToBackStack(loginFragment.getClass().getSimpleName())
//                                .commit();
//                        getSupportFragmentManager().beginTransaction().show(loginFragment).commit();
//                        getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                        fragmentTag = "login";
                        /*
                        profileFragment = ProfileFragment.newInstance(MainActivity.this);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.fragment_container,profileFragment,"home")
                                .addToBackStack(profileFragment.getClass().getSimpleName())
                                .commit();
                        getSupportFragmentManager().beginTransaction().show(profileFragment).commit();
                        getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                        fragmentTag = "profile";

                        */
                        break;
                    default:
                        return true;
                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment,fragmentTag).commit();
                return true;
            }
        });
        Log.i("logger", "MainActivity end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        //startLoginPage(LoginType.PHONE);
    }



    @Override
    public boolean onSupportNavigateUp() {
        finishAffinity();
        return true;
    }

    private void setFragment(Fragment fragment){
        if (fragment instanceof HomeFragment){

        }
    }

    public void loadHome(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    public void startLoginPage(LoginType loginType) {
        Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder builder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(loginType,AccountKitActivity.ResponseType.TOKEN);
        builder.setReadPhoneStateEnabled(true);
        builder.setEnableSms(true);
        builder.setDefaultCountryCode("BD");
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,builder.build());
        startActivityForResult(intent,REQUEST_CODE);
    }

    public static boolean hasPermissions(Context context, String... permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);

            if(result.getError() != null){
                Toast.makeText(this,result.getError().getErrorType().getMessage(),Toast.LENGTH_SHORT);
            }
            else if (result.wasCancelled()){
                Toast.makeText(this,"cancelled",Toast.LENGTH_SHORT);
            }
            else {
                if(result.getAccessToken() != null){
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(Account account) {
                            String id = account.getId();
                            String num = account.getPhoneNumber().toString();
                            Toast.makeText(getApplicationContext(),num,Toast.LENGTH_SHORT).show();
                            /*settings = getSharedPreferences("dhakasetup", Context.MODE_PRIVATE);
                            settings.edit().putString("userid",id).commit();
                            settings.edit().putString("phone",num).commit();
                            Fragment selectedFragment = new MiddleFragment();
                            fragmentTag = "middle";
                            bottomNavigationView.getMenu().getItem(1).setChecked(true);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment,fragmentTag).commit();
                            updateToken(settings.getString("phone",null),
                                    settings.getString("token",null),
                                    "online");*/
                        }

                        @Override
                        public void onError(AccountKitError accountKitError) {
                            /*Fragment selectedFragment = new ProfileFragment();
                            fragmentTag = "profile";
                            bottomNavigationView.getMenu().getItem(1).setChecked(true);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment,fragmentTag).commit();
                            */
                        }
                    });
                }
            }
        }
    }
}
