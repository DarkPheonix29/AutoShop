package com.example.autoshop;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
public interface RetrofitAPI {
    // as we are making a get request specifying annotation as get and adding a url end point to it.
    @GET("customers")
    // on below line specifying the method name which we have to call.
    Call<Customers> getData();

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("customers")

    //on below line we are creating a method to post our data.
    Call<Customers> createPost(@Body Customers customers);
}
