package com.dhakasetup.sakib.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TimingLogger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.adapter.AdAdapter;
import com.dhakasetup.sakib.adapter.CategoryAdapter;
import com.dhakasetup.sakib.adapter.RootAdapter;
import com.dhakasetup.sakib.model.HomeData;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView adRecycler;
    RecyclerView categoryRecycler;
    View view;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();

        Log.i("logger", "homeFragment start " + (System.currentTimeMillis() - HomeData.startTime) + " ms");

        RecyclerView recyclerView = view.findViewById(R.id.root_recycler);
        RootAdapter rootAdapter = new RootAdapter(context);
        recyclerView.setAdapter(rootAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Log.i("logger", "homeFragment end " + (System.currentTimeMillis() - HomeData.startTime) + " ms");

        return view;
    }

//    public void adSetup(){
//        adRecycler = view.findViewById(R.id.ad_recycler);
//        AdAdapter adAdapter = new AdAdapter(getActivity());
//        adRecycler.setAdapter(adAdapter);
//        adRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
//        TextView news = view.findViewById(R.id.breaking_news);
//        news.setSelected(true);
//    }
//
//    public void categorySetup(){
//        categoryRecycler = view.findViewById(R.id.cat_recycler);
//        CategoryAdapter categoryAdapter = new CategoryAdapter(context);
//        categoryRecycler.setAdapter(categoryAdapter);
//        categoryRecycler.setLayoutManager(new GridLayoutManager(context,4));
//        TextView news = view.findViewById(R.id.breaking_news);
//        news.setSelected(true);
//    }

}
