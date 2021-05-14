package com.example.g_market;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JustJsonPlaceHolderApi {

    @GET("search")
    Call<JustProducts> getJustProducts(@Query("query") String product_name);
}
