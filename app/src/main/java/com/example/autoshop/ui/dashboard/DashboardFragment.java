package com.example.autoshop.ui.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autoshop.ApiClient;
import com.example.autoshop.CredentialsResult;
import com.example.autoshop.MainActivity;
import com.example.autoshop.MyAdapter;
import com.example.autoshop.ProductScreen;
import com.example.autoshop.Products;
import com.example.autoshop.ProductsList;
import com.example.autoshop.RetrofitAPI;
import com.example.autoshop.ShoppingCart;
import com.example.autoshop.R;
import com.example.autoshop.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    MyAdapter myAdapter;
    RecyclerView recyclerView;

    private ShoppingCart shoppingCart;

    ArrayList<Products> products;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.recyclerview);

        // Initialize the products list
        products = new ArrayList<>();

        shoppingCart = new ShoppingCart();

        // Initialize the adapter after initializing the products list
        myAdapter = new MyAdapter(requireContext(), products, shoppingCart);


        // Set up the RecyclerView after initializing the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext()));
        recyclerView.setAdapter(myAdapter);

        // Call the method to fetch products
        getProducts();

        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        Button button1 = root.findViewById(R.id.Cart);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToQR();
            }
        });

        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    private void getProducts() {
        ApiClient.getClient().create(RetrofitAPI.class).getProductsData().enqueue(new Callback<ArrayList<Products>>() {
            @Override
            public void onResponse(Call<ArrayList<Products>> call, Response<ArrayList<Products>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Products> productList = response.body();

                    // Clear existing data before adding new data
                    products.clear();
                    products.addAll(productList);

                    // Notify the adapter that the data has changed
                    myAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Response", "Unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Products>> call, Throwable t) {
                Log.e("API Failure", t.getMessage());
            }
        });

    }







    private void switchToQR() {
        Intent intent = new Intent(requireContext(), ShoppingCart.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
