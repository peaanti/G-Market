package com.example.g_market;

import android.os.Parcel;
import android.os.Parcelable;

public class Top50Product implements Parcelable {
    private String releaseDate;
    private String region;
    private String url;
    private String title;
    private Integer numInStock;
    private String activation;
    private Boolean isAvailable;
    private String image;
    private Top50Prices prices;

    protected Top50Product(Parcel in) {
        releaseDate = in.readString();
        region = in.readString();
        url = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            numInStock = null;
        } else {
            numInStock = in.readInt();
        }
        activation = in.readString();
        byte tmpIsAvailable = in.readByte();
        isAvailable = tmpIsAvailable == 0 ? null : tmpIsAvailable == 1;
        image = in.readString();
    }

    public static final Creator<Top50Product> CREATOR = new Creator<Top50Product>() {
        @Override
        public Top50Product createFromParcel(Parcel in) {
            return new Top50Product(in);
        }

        @Override
        public Top50Product[] newArray(int size) {
            return new Top50Product[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(releaseDate);
        dest.writeString(region);
        dest.writeString(url);
        dest.writeString(title);
        if (numInStock == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numInStock);
        }
        dest.writeString(activation);
        dest.writeByte((byte) (isAvailable == null ? 0 : isAvailable ? 1 : 2));
        dest.writeString(image);
    }
}
