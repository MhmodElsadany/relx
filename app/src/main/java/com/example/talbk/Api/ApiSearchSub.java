package com.example.talbk.Api;

import com.example.talbk.Model.Product;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiSearchSub {
    String BASE_URL = "http://www.egyvenom.net";

    @POST("/api/Products/SearchBYSubCategoryID")
    Observable<ArrayList<Product>> getProduct(@Query("id") String id, @Query("text") String name);

}

