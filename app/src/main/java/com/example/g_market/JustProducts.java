package com.example.g_market;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JustProducts {
    @SerializedName("products")
    @Expose
    private List<JustProduct> justProducts = null;

    public List<JustProduct> getjustProducts() {
        return justProducts;
    }

}
