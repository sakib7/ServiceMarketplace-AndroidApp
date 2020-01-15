package com.dhakasetup.sakib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    private String id,name,image;
    @SerializedName("subcates")
    private List<Subcategory> subcategoryList;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Subcategory> getSubcategoryList() {
        return subcategoryList;
    }

    public void setSubcategoryList(List<Subcategory> subcategoryList) {
        this.subcategoryList = subcategoryList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' + "\n" +
                ", subcategoryList=" + subcategoryList +
                '}'+ "\n" + "-----------------------------";
    }
}
