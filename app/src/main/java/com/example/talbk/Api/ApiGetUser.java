package com.example.talbk.Api;

import com.example.talbk.Model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiGetUser {
    String BASE_URL = "http://www.egyvenom.net/";

    @GET("api/user/GetUserById/{id}")
    Observable<User> getUser(@Path("id") String id);

}
