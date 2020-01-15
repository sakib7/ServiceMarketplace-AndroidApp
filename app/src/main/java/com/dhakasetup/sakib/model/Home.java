package com.dhakasetup.sakib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Home {
    @SerializedName("ads")
    List<Ad> adList;
    @SerializedName("cats")
    List<Category> categoryList;
    @SerializedName("trendings")
    List<Trending> trendingList;

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Trending> getTrendingList() {
        return trendingList;
    }

    public void setTrendingList(List<Trending> trendingList) {
        this.trendingList = trendingList;
    }

    @Override
    public String toString() {
        return "Home{" + "\n" +
                "adList=" + adList + "\n" +
                ", categoryList=" + categoryList + "\n" +
                ", trendingList=" + trendingList + "\n" +
                '}';
    }
}
