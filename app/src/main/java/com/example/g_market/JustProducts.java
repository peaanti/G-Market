package com.example.g_market;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JustProducts {
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("products")
    @Expose
    private List<JustProduct> justProducts = null;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public List<JustProduct> getjustProducts() {
        return justProducts;
    }

    public void setJustProducts(List<JustProduct> justProducts) {
        this.justProducts = justProducts;
    }
}
