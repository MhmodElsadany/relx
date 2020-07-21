package com.example.talbk.Api;

import com.example.talbk.Model.Check;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiCheck {

    String BASE_URL = "http://www.egyvenom.net";

    @POST("/api/order/insertorder")
    Observable<String> createCheck(@Body Check check);
}



