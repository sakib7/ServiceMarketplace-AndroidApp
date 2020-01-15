package com.dhakasetup.sakib.model;

public class TrendingService {
    private String id,service_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    @Override
    public String toString() {
        return "TrendingService{" +
                "id='" + id + '\'' +
                ", service_id='" + service_id + '\'' +
                '}' + "\n";
    }
}
