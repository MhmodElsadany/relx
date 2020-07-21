package com.example.talbk.Api;

import com.example.talbk.Model.CateogryModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CategoryApi {

    String BASE_URL = "http://www.egyvenom.net/";

    @GET("api/category/GetAllData")
    Observable<ArrayList<CateogryModel>> getCategory();

}
