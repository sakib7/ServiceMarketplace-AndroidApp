package com.dhakasetup.sakib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.dhakasetup.sakib.adapter.ServiceAdapter;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.model.Service;
import com.dhakasetup.sakib.model.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryActivity extends AppCompatActivity {
    Subcategory subcategory;
    List<Service> serviceList;
    String subcategory_id;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        subcategory_id = getIntent().getStringExtra("subcategory_id");
        String trend = getIntent().getStringExtra("trend");
        String trendName = getIntent().getStringExtra("trend_name");

        if (trend == null){
            subcategory = HomeData.getSubcategory(subcategory_id, this);
            serviceList = subcategory.getServiceList();
            getSupportActionBar().setTitle(subcategory.getName());
        } else {
            serviceList = HomeData.getTrendingServices(trend,this);
            getSupportActionBar().setTitle(trendName);
        }

        ExpandableListView expandableListView = findViewById(R.id.exp_listview);
        ServiceAdapter serviceAdapter = new ServiceAdapter(serviceList,this);
        expandableListView.setAdapter(serviceAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
