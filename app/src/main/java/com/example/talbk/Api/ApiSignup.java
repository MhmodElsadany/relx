package com.example.talbk.Api;

import com.example.talbk.Model.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiSignup {
    String BASE_URL = "http://www.egyvenom.net";

    @POST("/api/user/adduser")
    Observable<String> createUser(@Body User user);
}



