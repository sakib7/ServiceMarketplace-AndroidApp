package com.dhakasetup.sakib.model;

public class VariantB {
    private String id,name;

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

    @Override
    public String toString() {
        return "VariantB{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}' + "\n";
    }
}
