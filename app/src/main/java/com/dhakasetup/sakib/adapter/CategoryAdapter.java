package com.dhakasetup.sakib.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhakasetup.sakib.CategoryActivity;
import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.model.Category;
import com.dhakasetup.sakib.model.HomeData;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryVH>{
    Context context;
    List<Category> categoryList;
    Category category;

    public CategoryAdapter(Context context){
        this.context = context;
        categoryList = HomeData.getHome(context).getCategoryList();
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("logger", "Category create start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_single,parent,false);
        Log.i("logger", "Category create end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, final int position) {
        Log.i("logger", "Category start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
        category = categoryList.get(position);
        holder.textView.setText(category.getName());
        Picasso.get().
                load("http://dhakasetup.com/images/category/"+category.getImage()).
                resize(100,70).
                centerCrop().
                into(holder.imageView);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = categoryList.get(position);
                Intent intent = new Intent(context,CategoryActivity.class);
                intent.putExtra("category",category.getName());
                context.startActivity(intent);
            }
        });
        Log.i("logger", "Category end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryVH extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        CardView root;
        public CategoryVH(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.category_grid_image);
            textView = itemView.findViewById(R.id.category_grid_text);
            root = itemView.findViewById(R.id.category_grid_root);
        }
    }
}
