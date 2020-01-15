package com.dhakasetup.sakib.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeData {

    static Home home = null;
    public static long startTime;

    public static Home getHome(Context context){
        if (home == null){
            String homeData = context.getSharedPreferences("dhakasetup",MODE_PRIVATE)
                    .getString("home",null);
            Gson gson = new Gson();
            home = gson.fromJson(homeData,Home.class);
            Toast.makeText(context,"cache cleared",Toast.LENGTH_SHORT).show();
        }
        return home;
    }
    public static void putHome(Context context){
        String homeData = context.getSharedPreferences("dhakasetup",MODE_PRIVATE)
                .getString("home",null);
        Gson gson = new Gson();
        home = gson.fromJson(homeData,Home.class);
    }

    public static VariantPrice getVariantPrice(String id, Context context){
        Home home = getHome(context);
        for (Category category : home.getCategoryList()){
            for (Subcategory subcategory : category.getSubcategoryList()){
                for (Service service : subcategory.getServiceList()){
                    for (VariantPrice variantPrice : service.getVariantPriceList()){
                        if (variantPrice.getId().equals(id)){
                            return variantPrice;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static VariantPrice getVariantPrice(String aid, String bid, Context context){
        Home home = getHome(context);
        for (Category category : home.getCategoryList()){
            for (Subcategory subcategory : category.getSubcategoryList()){
                for (Service service : subcategory.getServiceList()){
                    for (VariantPrice variantPrice : service.getVariantPriceList()){
                        if (variantPrice.getVariant_a_id().equals(aid) && variantPrice.getVariant_b_id().equals(bid)){
                            return variantPrice;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static Service getService(String id, Context context){
        Home home = getHome(context);
        List<Service> services = new ArrayList<>();
        for (Category category : home.getCategoryList()){
            for (Subcategory subcategory : category.getSubcategoryList()){
                for (Service service : subcategory.getServiceList()){
                    services.add(service);
                }
            }
        }
        for (Service service : services){
            if (service.getId().equals(id))
                return service;
        }
        return null;
    }

    public static Subcategory getSubcategory(String id, Context context){
        Home home = getHome(context);
        for (Category category : home.getCategoryList()){
            for (Subcategory subcategory : category.getSubcategoryList()){
                if (subcategory.getId().equals(id)){
                    return subcategory;
                }
            }
        }
        return null;
    }

    public static List<Service> getTrendingServices(String id, Context context){
        Home home = getHome(context);
        List<Service> services = new ArrayList<>();
        for (Trending trending : home.getTrendingList()){
            if (trending.getId().equals(id)){
                for (TrendingService trendingService : trending.getTrendingServiceList()) {
                    String service_id = trendingService.getService_id();
                    services.add(getService(service_id,context));
                }
            }
        }
        return services;
    }
}
