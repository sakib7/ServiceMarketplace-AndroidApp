package com.dhakasetup.sakib.model;

import com.google.gson.annotations.SerializedName;

public class VariantPrice {
    @SerializedName("variant_price_id")
    private String id;

    @SerializedName("variant_price_price")
    private String price;

    @SerializedName("service_id")
    private String service_id;

    @SerializedName("service_name")
    private String service_name;

    @SerializedName("service_image")
    private String service_image;

    @SerializedName("variant_a_id")
    private String variant_a_id;

    @SerializedName("variant_a_name")
    private String variant_a_name;

    @SerializedName("variant_b_id")
    private String variant_b_id;

    @SerializedName("variant_b_name")
    private String variant_b_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_image() {
        return service_image;
    }

    public void setService_image(String service_image) {
        this.service_image = service_image;
    }

    public String getVariant_a_id() {
        return variant_a_id;
    }

    public void setVariant_a_id(String variant_a_id) {
        this.variant_a_id = variant_a_id;
    }

    public String getVariant_a_name() {
        return variant_a_name;
    }

    public void setVariant_a_name(String variant_a_name) {
        this.variant_a_name = variant_a_name;
    }

    public String getVariant_b_id() {
        return variant_b_id;
    }

    public void setVariant_b_id(String variant_b_id) {
        this.variant_b_id = variant_b_id;
    }

    public String getVariant_b_name() {
        return variant_b_name;
    }

    public void setVariant_b_name(String variant_b_name) {
        this.variant_b_name = variant_b_name;
    }

    @Override
    public String toString() {
        return "VariantPrice{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                '}' + "\n";
    }
}
