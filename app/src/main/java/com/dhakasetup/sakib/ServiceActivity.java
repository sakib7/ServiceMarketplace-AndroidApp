package com.dhakasetup.sakib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.agrawalsuneet.dotsloader.loaders.TashieLoader;
import com.dhakasetup.sakib.helper.MenuPainter;
import com.dhakasetup.sakib.model.Cart;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.model.Service;

import java.text.NumberFormat;

public class ServiceActivity extends AppCompatActivity {
    Toolbar toolbar;
    Context context;
    NumberFormat nf;
    ImageButton plus_btn, minus_btn,add_more;
    TextView count_tv;
    TextView subtotal,savings;
    Button place_order_btn;
    TashieLoader tashieLoader;
    int counter = 0;
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String serviceID = getIntent().getStringExtra("service_id");
        service = HomeData.getService(serviceID,this);
        getSupportActionBar().setTitle(service.getName());
        context = this;

        tashieLoader = findViewById(R.id.textloader);

        subtotal = findViewById(R.id.service1_item_subtotal);
        savings = findViewById(R.id.service1_item_savings);
        add_more = findViewById(R.id.service1_item_add_more);
        plus_btn = findViewById(R.id.service1_item_plus_no);
        minus_btn = findViewById(R.id.service1_item_minus_no);
        count_tv = findViewById(R.id.service1_item_count_no);
        place_order_btn = findViewById(R.id.place_order_btn);
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
                if (count_tv.getText().equals("0")){
                    counter = Integer.valueOf(service.getQuantity());
                    invalidateOptionsMenu();
                } else {
                    counter = Integer.valueOf(count_tv.getText().toString()) + 1;
                }
                Cart.getCart().put(service.getId(), counter);
                count_tv.setText(String.valueOf(counter));
                subtotal.setText(nf.format(Cart.getTotal(context)));
            }
        });

        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count_tv.getText().equals("0"))
                    return;
                if (count_tv.getText().equals(service.getQuantity())){
                    counter = 0;
                    Cart.getCart().remove(service.getId());
                    invalidateOptionsMenu();
                } else {
                    counter = Integer.valueOf(count_tv.getText().toString()) - 1;
                    Cart.getCart().put(service.getId(), counter);
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
        place_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter%2==0){
                    tashieLoader.setVisibility(View.VISIBLE);
                    savings.setVisibility(View.INVISIBLE);
                } else{
                    tashieLoader.setVisibility(View.INVISIBLE);
                    savings.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_cart);
        menuItem.setIcon(MenuPainter.paint(ServiceActivity.this,
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
        //Toast.makeText(this,"srvItemactivty",Toast.LENGTH_SHORT).show();
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
