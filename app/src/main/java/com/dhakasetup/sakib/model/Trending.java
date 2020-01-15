package com.dhakasetup.sakib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trending {
    private String id,name;
    @SerializedName("trending_services")
    List<TrendingService> trendingServiceList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrendingService> getTrendingServiceList() {
        return trendingServiceList;
    }

    public void setTrendingServiceList(List<TrendingService> trendingServiceList) {
        this.trendingServiceList = trendingServiceList;
    }

    @Override
    public String toString() {
        return "Trending{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' + "\n" +
                ", trendingServiceList=" + trendingServiceList +
                '}' + "\n";
    }
}
