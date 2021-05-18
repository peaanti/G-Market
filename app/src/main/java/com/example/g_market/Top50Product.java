package com.example.g_market;

public class Top50Product {
    private String releaseDate;
    private String region;
    private String url;
    private String title;
    private Integer numInStock;
    private String activation;
    private Boolean isAvailable;
    private String image;
    private Top50Prices prices;

    public String getReleaseDate() { return releaseDate; }

    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getRegion() { return region; }

    public void setRegion(String region) { this.region = region; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Integer getNumInStock() { return numInStock; }

    public void setNumInStock(Integer numInStock) { this.numInStock = numInStock; }

    public String getActivation() { return activation; }

    public void setActivation(String activation) { this.activation = activation; }

    public Boolean getIsAvailable() { return isAvailable; }

    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public Top50Prices getPrices() { return prices; }

    public void setPrices(Top50Prices prices) { this.prices = prices; }
}
