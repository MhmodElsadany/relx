package com.example.talbk.Api;

import com.example.talbk.Model.UserOrder;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiOrders {
    String BASE_URL = "http://www.egyvenom.net";

    @GET("api/order/GetUserOrders/{id}")
    Observable<ArrayList<UserOrder>> getOrders(@Path("id") String id);

}

