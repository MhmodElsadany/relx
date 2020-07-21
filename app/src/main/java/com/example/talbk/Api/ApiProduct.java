package com.example.talbk.Api;


import com.example.talbk.Model.Product;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiProduct {
    String BASE_URL = "http://www.egyvenom.net";

    /*@GET("api/Products/getProductsBySubCatId/{id}")
    Observable<ArrayList<Product>> getProduct(@Path("id") String id);*/

    @GET("api/Products/getProductsBySubCatId")
    Observable<ArrayList<Product>> getProduct(@Query("id") String id,
                                              @Query("page") String page,
                                              @Query("size") String size);

}








