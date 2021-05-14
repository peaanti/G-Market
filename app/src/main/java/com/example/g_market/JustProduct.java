package com.example.g_market;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JustProduct {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("num_in_stock")
    @Expose
    private Integer numInStock;
    @SerializedName("activation")
    @Expose
    private String activation;
    @SerializedName("is_available")
    @Expose
    private Boolean isAvailable;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("prices")
    @Expose
    private Prices prices;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumInStock() {
        return numInStock;
    }

    public void setNumInStock(Integer numInStock) {
        this.numInStock = numInStock;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }
}
