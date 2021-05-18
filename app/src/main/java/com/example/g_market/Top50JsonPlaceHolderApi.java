package com.example.g_market;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Top50JsonPlaceHolderApi {

    @GET("top")
    Call<Top50Products> getTop50Products();
}
