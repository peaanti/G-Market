package com.example.g_market;

import org.jetbrains.annotations.NotNull;

public class Product {

    private String url;
    private String title;
    private String is_available;
    private Prices prices;


    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getIs_available() {
        return is_available;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIs_available(String is_available) {
        this.is_available = is_available;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    @NotNull
    @Override
    public String toString() {
        return "Product{" +
                "title=" + title +
                ", url='" + url + '\'' +
                ", price='" + prices + '\'' +
                '}';
    }

}
