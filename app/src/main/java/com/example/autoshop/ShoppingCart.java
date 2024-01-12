package com.example.autoshop;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;


import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Products> cartItems = new ArrayList<>();
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_shopping);

        Log.d("ShoppingCart", "Activity created");

        // Initialize the recyclerView
        recyclerView = findViewById(R.id.cartRecyclerView);

        if (recyclerView != null) {
            // Initialize cartAdapter
            cartAdapter = new CartAdapter(cartItems);

            // Set the adapter to recyclerView
            recyclerView.setAdapter(cartAdapter);

            // Set the layout manager
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            Log.d("ShoppingCart", "RecyclerView and Adapter initialized");
        } else {
            Log.e("ShoppingCart", "RecyclerView is null");
        }

        // Rest of your code...
    }

    // ... Other methods in your ShoppingCart activity

    public void addItemToCart(Products product) {
        // Add a log statement to check if addItemToCart is being called
        Log.d("ShoppingCart", "Item added to cart: " + product.getName());

        if (cartItems == null) {
            Log.e("ShoppingCart", "cartItems is null");
        } else {
            // Add a log statement to check cartItems before adding the item
            Log.d("ShoppingCart", "Cart items before adding: " + cartItems.toString());

            cartItems.add(product);

            // Add a log statement to check cartItems after adding the item
            Log.d("ShoppingCart", "Cart items after adding: " + cartItems.toString());
        }

        // Notify the adapter
        if (cartAdapter != null) {
            cartAdapter.notifyDataSetChanged();
            Log.d("ShoppingCart", "Adapter notified of data change");
        }
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureACt.class);
        barlauncher.launch(options);
    }

    // ... Other methods in your ShoppingCart activity

    ActivityResultLauncher<ScanOptions> barlauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).show();
        }
    });
}