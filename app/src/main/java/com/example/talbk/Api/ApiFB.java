package com.example.talbk.Api;

import com.example.talbk.Model.Feedback;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiFB {
    String BASE_URL = "http://www.egyvenom.net";

    @POST("/api/feedback/addfeedback")
    Observable<String> createUser(@Body Feedback feedback);

}
