package com.example.talbk.Api;

import com.example.talbk.Model.SubCatregoryModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiSub {

    String BASE_URL = "http://www.egyvenom.net/";

    @GET("api/subcategory/GetSubCategoryById/{id}")
    Observable<ArrayList<SubCatregoryModel>> getSubCategory(@Path("id") String id);

}


