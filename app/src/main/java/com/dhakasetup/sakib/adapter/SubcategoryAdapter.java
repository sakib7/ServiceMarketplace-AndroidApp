package com.dhakasetup.sakib.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.SubcategoryActivity;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.model.Subcategory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubcatVH> {
    List<Subcategory> subcategories;
    Context context;
    String category;

    public SubcategoryAdapter(List<Subcategory> subcategories, Context context, String category) {
        this.subcategories = subcategories;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public SubcatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subcategory_single,parent,false);
        return new SubcatVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcatVH holder, final int position) {
        Subcategory subcategory = subcategories.get(position);
        holder.title.setText(subcategory.getName());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subcategory subcategory = subcategories.get(position);
                Toast.makeText(context,subcategory.getId(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,SubcategoryActivity.class);
                intent.putExtra("subcategory_id",subcategory.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subcategories.size();
    }

    public class SubcatVH extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        LinearLayout root;

        public SubcatVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.subcat_image);
            title = itemView.findViewById(R.id.subcat_name);
            root = itemView.findViewById(R.id.subcat_root);
        }
    }
}
