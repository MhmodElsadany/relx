package com.example.talbk.Api;

import com.example.talbk.Model.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiUser {
    String BASE_URL = "http://www.egyvenom.net/";

    @POST("api/user/Login")
    Observable<String> checkUser(@Body User user);
}


