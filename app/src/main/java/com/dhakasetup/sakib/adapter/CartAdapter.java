package com.dhakasetup.sakib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhakasetup.sakib.R;
import com.dhakasetup.sakib.model.Cart;
import com.dhakasetup.sakib.model.CartItem;
import com.dhakasetup.sakib.model.HomeData;
import com.dhakasetup.sakib.model.Service;
import com.dhakasetup.sakib.model.VariantPrice;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartVH>{
    Context context;
    List<CartItem> cartItems;
    NumberFormat nf = NumberFormat.getInstance();

    public CartAdapter(Context context) {
        this.context = context;
        cartItems = Cart.getCartItems(context);
    }

    @NonNull
    @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_single,parent,false);
        return new CartAdapter.CartVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartVH holder, int position) {
        CartItem cartItem = cartItems.get(position);
        String image;
        if (cartItem.getVariantPrice() == null){
            holder.srvice.setText(cartItem.getService().getName());
            image = cartItem.getService().getImage();
        } else {
            String service = cartItem.getVariantPrice().getService_name();
            String vara = cartItem.getVariantPrice().getVariant_a_name();
            String varb = cartItem.getVariantPrice().getVariant_b_name();
            holder.srvice.setText(service + " ("+vara+" - "+varb+")");
            image = cartItem.getVariantPrice().getService_image();
        }
        Picasso.get().load("http://dhakasetup.com/images/services/"+image).into(holder.srvImg);
        holder.price.setText(nf.format(cartItem.getPrice()));
        holder.unit.setText(String.valueOf(cartItem.getCount()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartVH extends RecyclerView.ViewHolder{
        ImageView srvImg;
        TextView srvice,unit,price;
        RelativeLayout root;
        public CartVH(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.cart_root);
            srvImg = itemView.findViewById(R.id.cart_item_image);
            srvice = itemView.findViewById(R.id.cart_item_srvice);
            unit = itemView.findViewById(R.id.cart_item_unit);
            price = itemView.findViewById(R.id.cart_item_amount);
        }
    }
}
