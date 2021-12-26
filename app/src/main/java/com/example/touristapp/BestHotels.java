package com.example.touristapp;

public class BestHotels {
    String placeName;
    String cityName;
    String price;
    Integer imageUrl;

    public BestHotels(String placeName, String cityName, String price, Integer imageUrl){
        this.placeName=placeName;
        this.cityName=cityName;
        this.price=price;
        this.imageUrl=imageUrl;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPrice() {
        return price;
    }
}
