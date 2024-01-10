package com.example.autoshop;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    // as we are making a get request specifying annotation as get and adding a url end point to it.
    @GET("api/customers")
    // on below line specifying the method name which we have to call.
    Call<Customers> getCustomersData();

    @GET("api/credentials/{email}&{password}")

    public Call<CredentialsResult> getCredentialsData(@Path("email") String email, @Path("password") String password);

    @GET("api/products")

    Call<ArrayList<Products>> getProductsData();



    @POST("api/customers")

    //on below line we are creating a method to post our data.
    Call<Customers> createPost(@Body Customers customers);

    @POST("api/credentials")

    Call<Credentials> createPost(@Body Credentials credentials);
}
