package com.example.talbk.Api;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiForget {

    String BASE_URL = "http://www.egyvenom.net";

    @POST("api/user/forgetpassword")
    Observable<String> getProduct(@Query("phone") String phone);

}
