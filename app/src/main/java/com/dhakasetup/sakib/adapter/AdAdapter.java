package com.dhakasetup.sakib.adapter;

import android.content.Context;
import android.util.Log;
import android.util.TimingLogger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.model.Ad;
import com.dhakasetup.sakib.model.HomeData;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdVH> {
    List<Ad> adList;
    Context context;

    public AdAdapter(Context context){
        this.context = context;
        adList = HomeData.getHome(context).getAdList();
    }

    @NonNull
    @Override
    public AdVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("logger", "Ad create start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        View view = LayoutInflater.from(context).inflate(R.layout.item_ad_single,parent,false);
        Log.i("logger", "Ad create end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        return new AdVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdVH holder, int position) {
        Log.i("logger", "Ad start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        ImageView imageView = holder.imageView;
        Picasso.get().load("http://dhakasetup.com/images/ad/"+adList.get(position).getAd_image()).into(holder.imageView);
        Log.i("logger", "Ad end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }

    public class AdVH extends RecyclerView.ViewHolder{
        ImageView imageView;
        public AdVH(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.ad_banner_img);
        }
    }
}
