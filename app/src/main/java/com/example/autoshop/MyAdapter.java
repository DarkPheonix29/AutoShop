package com.example.autoshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Products> products;
    private ShoppingCart shoppingCart;

    public MyAdapter(Context context, ArrayList<Products> arrayList, ShoppingCart shoppingCart) {
        this.context = context;
        this.products = arrayList;
        this.shoppingCart = shoppingCart;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(products.get(position));

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Products clickedProduct = products.get(clickedPosition);

                    // Add a log statement to check if onClick is triggered
                    Log.d("MyAdapter", "Add to cart button clicked for: " + clickedProduct.getName());

                    shoppingCart.addItemToCart(clickedProduct);

                    // Add a log statement to check if addItemToCart is called
                    Log.d("MyAdapter", "Item added to cart in ShoppingCart");
                    Toast.makeText(context, clickedProduct.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price;
        private Button addToCartButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemname);
            price = itemView.findViewById(R.id.itemprice);
            addToCartButton = itemView.findViewById(R.id.addbutton);
        }

        public void bind(Products product) {
            name.setText(product.getName());
            price.setText(String.valueOf(product.getPrice()));
        }
    }
}
