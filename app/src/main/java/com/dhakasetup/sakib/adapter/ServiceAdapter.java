package com.dhakasetup.sakib.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.ServiceActivity;
import com.dhakasetup.sakib.ServiceVariantActivity;
import com.dhakasetup.sakib.model.Service;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceAdapter extends BaseExpandableListAdapter {
    List<Service> services;
    Context context;

    public ServiceAdapter(List<Service> services, Context context) {
        this.services = services;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return services.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return services.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return services.get(groupPosition).getDetails();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView name,price;
        ImageView image;
        Button btn;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_expandable_parent,null);
        }
        name = convertView.findViewById(R.id.service1_item_name);
        price = convertView.findViewById(R.id.service1_item_price);
        image = convertView.findViewById(R.id.service1_item_imageSrv);
        btn = convertView.findViewById(R.id.service1_item_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (services.get(groupPosition).getVariantAList().size() == 0){
                    intent = new Intent(context, ServiceActivity.class);
                } else{
                    intent = new Intent(context, ServiceVariantActivity.class);
                }
                intent.putExtra("service_id",services.get(groupPosition).getId());
                context.startActivity(intent);
            }
        });
        name.setText(services.get(groupPosition).getName());
        price.setText("Starts at ৳"+services.get(groupPosition).getPrice());
        Picasso.get().load("http://dhakasetup.com/images/services/"+services.get(groupPosition).getImage()).into(image);
        btn.setText("Add");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_expandable_child,null);
        }
        TextView desc = convertView.findViewById(R.id.service1_item_child);
        desc.setText(services.get(groupPosition).getDetails());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
