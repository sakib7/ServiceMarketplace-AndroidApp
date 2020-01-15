package com.dhakasetup.sakib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dhakasetup.sakib.helper.MenuPainter;
import com.dhakasetup.sakib.model.Cart;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.model.Service;
import com.dhakasetup.sakib.model.VariantA;
import com.dhakasetup.sakib.model.VariantB;
import com.dhakasetup.sakib.model.VariantPrice;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ServiceVariantActivity extends AppCompatActivity {
    Toolbar toolbar;
    Context context;
    NumberFormat nf;
    ImageButton plus_btn, minus_btn,add_more;
    TextView count_tv;
    TextView subtotal,savings;
    TextView varaTitle,varbTitle;
    Spinner varaSpinner,varbSpinner;
    int counter = 0;
    Service service;
    List<VariantA> variantAList;
    List<VariantB> variantBList;
    List<String> varaIDs =new ArrayList<>();
    List<String> varaNames =new ArrayList<>();
    List<String> varbIDs =new ArrayList<>();
    List<String> varbNames =new ArrayList<>();
    VariantPrice variantPrice;
    String varaID,varbID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_variant);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String serviceID = getIntent().getStringExtra("service_id");
        service = HomeData.getService(serviceID,this);
        getSupportActionBar().setTitle(service.getName());
        context = this;

        subtotal = findViewById(R.id.service1_item_subtotal);
        savings = findViewById(R.id.service1_item_savings);
        add_more = findViewById(R.id.service1_item_add_more);
        plus_btn = findViewById(R.id.service1_item_plus_no);
        minus_btn = findViewById(R.id.service1_item_minus_no);
        count_tv = findViewById(R.id.service1_item_count_no);
        varaTitle = findViewById(R.id.vara_title);
        varbTitle = findViewById(R.id.varb_title);
        varaSpinner = findViewById(R.id.vara_spinner);
        varbSpinner = findViewById(R.id.varb_spinner);
        nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        if (Cart.getCart().get(service.getId()) != null){
            counter = Cart.getCart().get(service.getId());
        } else {
            counter = 0;
        }
        count_tv.setText(String.valueOf(counter));
        subtotal.setText(nf.format(Cart.getTotal(context)));

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variantPrice == null){
                    Toast.makeText(context,"Please select options",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (count_tv.getText().equals("0")){
                    counter = Integer.valueOf(service.getQuantity());
                    invalidateOptionsMenu();
                } else {
                    counter = Integer.valueOf(count_tv.getText().toString()) + 1;
                }
                Cart.getCartVariant().put(variantPrice.getId(), counter);
                count_tv.setText(String.valueOf(counter));
                subtotal.setText(nf.format(Cart.getTotal(context)));
            }
        });

        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variantPrice == null){
                    Toast.makeText(context,"Please select options",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (count_tv.getText().equals("0"))
                    return;
                if (count_tv.getText().equals(service.getQuantity())){
                    counter = 0;
                    Cart.getCartVariant().remove(variantPrice.getId());
                    invalidateOptionsMenu();
                } else {
                    counter = Integer.valueOf(count_tv.getText().toString()) - 1;
                    Cart.getCartVariant().put(variantPrice.getId(), counter);
                }
                count_tv.setText(String.valueOf(counter));
                subtotal.setText(nf.format(Cart.getTotal(context)));
            }
        });

        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        setupSpinner();
    }

    private void setupSpinner(){
        varaTitle.setText(service.getVar_a());
        varbTitle.setText(service.getVar_b());
        variantAList = service.getVariantAList();
        varbID = "0";
        varaIDs.add("0");
        varaNames.add("---"+service.getVar_a()+"---");
        for(VariantA variantA : variantAList){
            varaIDs.add(variantA.getId());
            varaNames.add(variantA.getName());
        }
        ArrayAdapter<String> varaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, varaNames);
        varaAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        varaSpinner.setAdapter(varaAdapter);

        variantBList = service.getVariantBList();
        varbID = "0";
        varbIDs.add("0");
        varbNames.add("---"+service.getVar_b()+"---");
        for(VariantB variantB : variantBList){
            varbIDs.add(variantB.getId());
            varbNames.add(variantB.getName());
        }
        ArrayAdapter<String> varbAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, varbNames);
        varbAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        varbSpinner.setAdapter(varbAdapter);

        varaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                varaID = varaIDs.get(position);
                onVariantSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        varbSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                varbID = varbIDs.get(position);
                onVariantSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onVariantSelected(){
        if (!varaID.equals("0") && !varbID.equals("0")){
            variantPrice = HomeData.getVariantPrice(varaID,varbID,this);
            Toast.makeText(context,variantPrice.getPrice(),Toast.LENGTH_SHORT).show();
        } else {
            variantPrice = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_cart);
        menuItem.setIcon(MenuPainter.paint(ServiceVariantActivity.this,
                Cart.getCart().size()+Cart.getCartVariant().size(),
                R.drawable.ic_shopping_cart_white_24dp));

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            //
        }
        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent intent = new Intent(this,CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_search:
//                Intent intent1 = new Intent(this,SearchActivity.class);
//                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
