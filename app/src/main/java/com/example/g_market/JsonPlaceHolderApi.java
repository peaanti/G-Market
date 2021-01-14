package com.example.g_market;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("products")
    Call<Products> getProducts();
}
