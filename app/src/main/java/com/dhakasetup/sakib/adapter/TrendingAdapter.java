package com.dhakasetup.sakib.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.ServiceActivity;
import com.dhakasetup.sakib.ServiceVariantActivity;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.model.Service;
import com.dhakasetup.sakib.model.TrendingService;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingVH> {
    Context context;
    List<TrendingService> trendingServiceList;

    public TrendingAdapter(Context context,int relative_position){
        this.context = context;
        trendingServiceList = HomeData.getHome(context).
                getTrendingList().
                get(relative_position).
                getTrendingServiceList();
    }

    @NonNull
    @Override
    public TrendingVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("logger", "Trending create start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_trending_single,parent,false);
        Log.i("logger", "Trending create end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        return new TrendingVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingVH holder, final int position) {
        Log.i("logger", "Trending start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        String serviceName = HomeData.
                getService(trendingServiceList.get(position).getService_id(),context).
                getName();
        holder.textView.setText(serviceName);
        String serviceImage = HomeData.
                getService(trendingServiceList.get(position).getService_id(),context).
                getImage();
        Picasso.get().load("http://dhakasetup.com/images/services/"+serviceImage).
                resize(100,70).
                centerCrop().
                into(holder.imageView);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                Service service = HomeData.getService(trendingServiceList.get(position).getService_id(),context);
                if (service.getVariantAList().size() == 0){
                    intent = new Intent(context, ServiceActivity.class);
                } else{
                    intent = new Intent(context, ServiceVariantActivity.class);
                }
                intent.putExtra("service_id",service.getId());
                context.startActivity(intent);
            }
        });
        Log.i("logger", "Trending end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
    }

    @Override
    public int getItemCount() {
        return trendingServiceList.size();
    }

    public class TrendingVH extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        LinearLayout root;
        public TrendingVH(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.trending_service_image);
            textView = itemView.findViewById(R.id.trending_service_name);
            root = itemView.findViewById(R.id.trending_service_root);
        }
    }
}
