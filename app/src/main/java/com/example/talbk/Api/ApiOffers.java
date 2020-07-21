package com.example.talbk.Api;


import com.example.talbk.Model.Product;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiOffers {

    String BASE_URL = "http://www.egyvenom.net";

    @GET("api/products/getoffers")
    Observable<ArrayList<Product>> getProduct();
}

