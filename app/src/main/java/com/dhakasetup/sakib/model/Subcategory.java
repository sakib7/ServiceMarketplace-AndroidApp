package com.dhakasetup.sakib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Subcategory {
    private String id,name,category_id;
    @SerializedName("services")
    List<Service> serviceList;

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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "id='" + id + '\'' +
                "category_id='" + category_id + '\'' +
                ", name='" + name + '\'' + "\n" +
                ", serviceList=" + serviceList +
                '}' + "\n";
    }
}
