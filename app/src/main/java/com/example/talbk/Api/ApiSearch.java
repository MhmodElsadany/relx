package com.example.talbk.Api;

import com.example.talbk.Model.Product;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiSearch {
    String BASE_URL = "http://www.egyvenom.net";

    @GET("api/products/searchproduct")
    Observable<ArrayList<Product>> getProduct(@Query("text") String id);

}
