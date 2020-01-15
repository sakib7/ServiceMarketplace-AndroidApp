package com.dhakasetup.sakib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Service {
    private String id,name,image,details,quantity,unit,price,var_a,var_b,subcategory_id;
    @SerializedName("variant_a")
    List<VariantA> variantAList;
    @SerializedName("variant_b")
    List<VariantB> variantBList;
    @SerializedName("variant_price")
    List<VariantPrice> variantPriceList;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVar_a() {
        return var_a;
    }

    public void setVar_a(String var_a) {
        this.var_a = var_a;
    }

    public String getVar_b() {
        return var_b;
    }

    public void setVar_b(String var_b) {
        this.var_b = var_b;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public List<VariantA> getVariantAList() {
        return variantAList;
    }

    public void setVariantAList(List<VariantA> variantAList) {
        this.variantAList = variantAList;
    }

    public List<VariantB> getVariantBList() {
        return variantBList;
    }

    public void setVariantBList(List<VariantB> variantBList) {
        this.variantBList = variantBList;
    }

    public List<VariantPrice> getVariantPriceList() {
        return variantPriceList;
    }

    public void setVariantPriceList(List<VariantPrice> variantPriceList) {
        this.variantPriceList = variantPriceList;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", details='" + details + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unit='" + unit + '\'' +
                ", price='" + price + '\'' +
                ", var_a='" + var_a + '\'' +
                ", var_b='" + var_b + '\'' +
                ", subcategory_id='" + subcategory_id + '\'' + "\n" +
                ", variantAList=" + variantAList + "\n" +
                ", variantBList=" + variantBList + "\n" +
                ", variantPriceList=" + variantPriceList +
                '}' + "\n";
    }
}
