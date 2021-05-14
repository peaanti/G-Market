package com.example.g_market;
public class Product {

    private String url;
    private String title;
    private String is_available;
    private Prices prices;
    public String image;


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

    public String getImage() { return image; }

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

    public void setImage(String image) { this.image = image; }


}
