package com.dhakasetup.sakib.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.TimingLogger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.SubcategoryActivity;
import com.dhakasetup.sakib.model.Home;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.model.Trending;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_AD = 0;
    private final int TYPE_CATEGORY = 1;
    private final int TYPE_TRENDING = 2;
    Context context;
    Home home;

    public RootAdapter(Context context) {
        this.context = context;
        home = HomeData.getHome(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("logger", "RootAdapter create start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType){
            case TYPE_AD:
                view = inflater.inflate(R.layout.item_ad,parent,false);
                viewHolder = new AdVH(view);
                break;
            case TYPE_CATEGORY:
                view = inflater.inflate(R.layout.item_category,parent,false);
                TextView news = view.findViewById(R.id.breaking_news);
                news.setSelected(true);
                viewHolder = new CategoryVH(view);
                break;
            default:
                view = inflater.inflate(R.layout.item_trending,parent,false);
                viewHolder = new TrendingVH(view);
                break;
        }
        Log.i("logger", "RootAdapter create end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        Log.i("logger", "RootAdapter start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        switch (viewType){
            case TYPE_AD:
                AdVH adVH = (AdVH) holder;
                AdAdapter adAdapter = new AdAdapter(context);
                adVH.recyclerView.
                        setLayoutManager(new LinearLayoutManager(context,
                                LinearLayoutManager.HORIZONTAL,false));
                adVH.recyclerView.setHasFixedSize(true);
                adVH.recyclerView.setAdapter(adAdapter);
                break;
            case TYPE_CATEGORY:
                CategoryVH categoryVH = (CategoryVH) holder;
                categoryVH.recyclerView.setLayoutManager(new GridLayoutManager(context,4));
                CategoryAdapter categoryAdapter = new CategoryAdapter(context);
                categoryVH.recyclerView.setHasFixedSize(true);
                categoryVH.recyclerView.setAdapter(categoryAdapter);
                break;
            default:
                TrendingVH trendingVH = (TrendingVH) holder;
                final Trending trending = HomeData.getHome(context).getTrendingList().get(position-2);
                trendingVH.title.setText(trending.getName());
                trendingVH.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                TrendingAdapter trendingAdapter = new TrendingAdapter(context,position-2);
                trendingVH.recyclerView.setHasFixedSize(true);
                trendingVH.recyclerView.setAdapter(trendingAdapter);
                trendingVH.see_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context,""+serviceGroup.getTrend_id(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, SubcategoryActivity.class);
                        intent.putExtra("trend",trending.getId());
                        intent.putExtra("trend_name",trending.getName());
                        context.startActivity(intent);
                    }
                });
                break;
        }
        Log.i("logger", "RootAdapter end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
    }

    @Override
    public int getItemCount() {
        return home.getTrendingList().size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if(position == 0){
            type = TYPE_AD;
        }
        else if (position == 1){
            type = TYPE_CATEGORY;
        }
        else{
            type = TYPE_TRENDING;
        }
        return type;
    }

    public class AdVH extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public AdVH(View itemView){
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_ad);
        }
    }
    public class CategoryVH extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public CategoryVH(View itemView){
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_category);
        }
    }
    public class TrendingVH extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView;
        public TextView title;
        public Button see_all;

        public TrendingVH(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_trending);
            title = itemView.findViewById(R.id.trending_title);
            see_all = itemView.findViewById(R.id.trending_see_all);
        }
    }
}
