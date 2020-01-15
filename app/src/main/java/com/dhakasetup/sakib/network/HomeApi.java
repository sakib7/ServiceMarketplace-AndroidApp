package com.dhakasetup.sakib.network;

import com.dhakasetup.sakib.model.Home;
import com.dhakasetup.sakib.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HomeApi {
    @GET("service.php")
    Call<Home> getHome();

    @POST("profile/userlogin.php")
    Call<User> loginUser(@Body User user);

    @POST("profile/get-profile-data.php")
    Call<User> getProfile(@Body User user);

    @POST("profile/post-profile-data.php")
    Call<User> postProfile(@Body User user);
}
