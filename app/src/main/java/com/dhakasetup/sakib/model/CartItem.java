package com.dhakasetup.sakib.model;

public class CartItem {
    Service service;
    VariantPrice variantPrice;
    int count;
    double price;

    public CartItem(Service service, int count, double price) {
        this.service = service;
        this.count = count;
        this.price = price;
    }

    public CartItem(VariantPrice variantPrice, int count, double price) {
        this.variantPrice = variantPrice;
        this.count = count;
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public VariantPrice getVariantPrice() {
        return variantPrice;
    }

    public void setVariantPrice(VariantPrice variantPrice) {
        this.variantPrice = variantPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
