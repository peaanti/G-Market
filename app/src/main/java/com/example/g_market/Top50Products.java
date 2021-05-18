package com.example.g_market;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Top50Products {
    @SerializedName("products")
    @Expose
    private final List<Top50Product> Top50Products = null;

    public List<Top50Product> getTop50Products() { return Top50Products; }

}
