package com.dhakasetup.sakib.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private static HashMap<String,Integer> cartMap;
    private static HashMap<String,Integer> variantMap = new HashMap<>();

    public static HashMap<String,Integer> getCart(){
        if (cartMap == null)
            cartMap = new HashMap<>();
        return cartMap;
    }

    public static HashMap<String,Integer> getCartVariant(){
        if (variantMap == null)
            variantMap = new HashMap<>();
        return variantMap;
    }

    public static Double getTotal(Context context){
        Double total = 0.0;
        for(Map.Entry<String,Integer> entry: cartMap.entrySet()){
            String service_id = entry.getKey();
            Integer count = entry.getValue();
            Double price = Double.valueOf(HomeData.getService(service_id,context).getPrice());
            total += price * count;
        }
        for(Map.Entry<String,Integer> entry: variantMap.entrySet()){
            String vp_id = entry.getKey();
            Integer count = entry.getValue();
            Double price = Double.valueOf(HomeData.getVariantPrice(vp_id,context).getPrice());
            total += price * count;
        }
        return total;
    }

    public static List<CartItem> getCartItems(Context context){
        List<CartItem> cartItems = new ArrayList<>();
        for(Map.Entry<String,Integer> entry: cartMap.entrySet()){
            String service_id = entry.getKey();
            Integer count = entry.getValue();
            Service service = HomeData.getService(service_id,context);
            Double price = Double.valueOf(service.getPrice());
            CartItem cartItem = new CartItem(service,count,price*count);
            cartItems.add(cartItem);
        }
        for(Map.Entry<String,Integer> entry: variantMap.entrySet()){
            String vp_id = entry.getKey();
            Integer count = entry.getValue();
            VariantPrice variantPrice = HomeData.getVariantPrice(vp_id,context);
            Double price = Double.valueOf(variantPrice.getPrice());
            CartItem cartItem = new CartItem(variantPrice,count,price*count);
            cartItems.add(cartItem);
        }
        return cartItems;
    }

}
