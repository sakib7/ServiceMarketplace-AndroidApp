package com.dhakasetup.sakib.model;

public class Ad {
    private String id,ad_name,ad_image,link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_image() {
        return ad_image;
    }

    public void setAd_image(String ad_image) {
        this.ad_image = ad_image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id='" + id + '\'' +
                ", ad_name='" + ad_name + '\'' +
                ", ad_image='" + ad_image + '\'' +
                ", link='" + link + '\'' +
                '}' + "\n";
    }
}
