package com.dhakasetup.sakib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.dhakasetup.sakib.adapter.SubcategoryAdapter;
import com.dhakasetup.sakib.adapter.ViewPagerAdapter;
import com.dhakasetup.sakib.fragment.CategoryFragment;
import com.dhakasetup.sakib.model.Category;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.model.Subcategory;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    Subcategory adapter;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Services");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        List<Category> categories = HomeData.getHome(this).getCategoryList();
        for (Category category : categories){
            List<Subcategory> subcategories = category.getSubcategoryList();
            Fragment fragment = CategoryFragment.newInstance(category.getId(),subcategories);
            viewPagerAdapter.addFragment(fragment,category.getName());
            tabLayout.addTab(tabLayout.newTab().setText(category.getName()));
        }

        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        this.viewPager.setAdapter(viewPagerAdapter);

        this.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(this.viewPager));

        for (int i=0; i<tabLayout.getTabCount(); i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if(tab.getText().equals(getIntent().getStringExtra("category")))
                tab.select();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
