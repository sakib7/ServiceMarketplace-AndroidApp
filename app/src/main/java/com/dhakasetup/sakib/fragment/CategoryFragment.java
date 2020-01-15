package com.dhakasetup.sakib.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.adapter.SubcategoryAdapter;
import com.dhakasetup.sakib.model.Subcategory;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    List<Subcategory> subcategoryList;


    public CategoryFragment() {
        subcategoryList = new ArrayList<>();
    }


    public static CategoryFragment newInstance(String number, List<Subcategory> subcategories) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.subcategoryList = subcategories;
        Bundle args = new Bundle();
        args.putString("number", number);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.subcategory_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SubcategoryAdapter adapter = new SubcategoryAdapter(subcategoryList,getActivity(),getArguments().getString("number"));
        recyclerView.setAdapter(adapter);
        return view;
    }

}
