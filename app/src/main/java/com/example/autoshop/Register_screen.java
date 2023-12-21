package com.example.autoshop;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register_screen extends AppCompatActivity {

    private CheckBox agreeCheckBox;
    private TextView agreeMessage;
    private EditText emailEdt, passEdt, adressEdt, cntryEdt, postalEdt, genderEdt, firstEdt, lastEdt, birthEdt;
    private Button postDataBtn;

    private TextView responseTV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        agreeCheckBox = findViewById(R.id.checkBox);
        agreeMessage = findViewById(R.id.agree_message);
        emailEdt = findViewById(R.id.email_register);
        passEdt = findViewById(R.id.password_register);
        adressEdt = findViewById(R.id.adress_register);
        cntryEdt = findViewById(R.id.country_register);
        postalEdt = findViewById(R.id.postalcode_register);
        genderEdt = findViewById(R.id.gender_register);
        firstEdt = findViewById(R.id.firstname_register);
        lastEdt = findViewById(R.id.lastname_register);
        birthEdt = findViewById(R.id.birthday_register);
        postDataBtn = findViewById(R.id.button);
        loadingPB = findViewById(R.id.LoadingPB);
        responseTV = findViewById(R.id.idTVResponse);

        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (emailEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()) {
                    Toast.makeText(Register_screen.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postData(emailEdt.getText().toString(), passEdt.getText().toString(), adressEdt.getText().toString(), cntryEdt.getText().toString(), postalEdt.getText().toString(), genderEdt.getText().toString(), firstEdt.getText().toString(), lastEdt.getText().toString(), birthEdt.getText().toString());
            }
        });
    }

    public void switchToMainActivity(View view) {
        if (agreeCheckBox.isChecked()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            // Show the message
            agreeMessage.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void postData(String email, String password, String adress, String country, String postalcode, String gender, String first_name, String last_name, String birthday) {
        // below line is for displaying our progress bar.
        loadingPB.setVisibility(View.VISIBLE);


        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.160.50:8080/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last, we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        Customers customersModel = new Customers(email, adress, country, postalcode, gender, first_name, last_name, birthday);
        Call<Customers> customersCall = retrofitAPI.createPost(customersModel);

        Credentials credentialsModel = new Credentials(email, password);
        Call<Credentials> credentialsCall = retrofitAPI.createPost(credentialsModel);
        // calling a method to create a post and passing our modal class.


        // on below line we are executing our method.
        customersCall.enqueue(new Callback<Customers>() {
            @Override
            public void onResponse(Call<Customers> call, Response<Customers> response) {
                // this method is called when we get a response from our API.
                Toast.makeText(Register_screen.this, "Account created", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                emailEdt.setText("");
                passEdt.setText("");
                adressEdt.setText("");
                cntryEdt.setText("");
                postalEdt.setText("");
                genderEdt.setText("");
                firstEdt.setText("");
                lastEdt.setText("");
                birthEdt.setText("");

                if (response.isSuccessful()) {
                    Customers responseFromAPI = response.body();
                    // on below line we are getting our data from model class and adding it to our string.
                    String responseString = "Response Code : " + response.code() + "\nEmail : " + responseFromAPI.getEmail() + "\n" + "Adress : " + responseFromAPI.getAdress() + "\n" + "Country : " + responseFromAPI.getCountry() + "\n" + "Postal Code : " + responseFromAPI.getPostalcode();

                    // below line we are setting our
                    // string to our text view.
                    responseTV.setText(responseString);

                    // Switch to MainActivity
                    switchToMainActivity(null);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(Register_screen.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Customers> call, Throwable t) {
                // setting text to our text view when
                // we get an error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });

        credentialsCall.enqueue(new Callback<Credentials>() {
            @Override
            public void onResponse(Call<Credentials> call, Response<Credentials> response) {
                // this method is called when we get a response from our API.
                Toast.makeText(Register_screen.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                emailEdt.setText("");
                passEdt.setText("");


                if (response.isSuccessful()) {
                    Credentials responseFromAPI = response.body();
                    // on below line we are getting our data from model class and adding it to our string.
                    String responseString = "Response Code : " + response.code() + "\nEmail : " + responseFromAPI.getEmail();

                    // below line we are setting our
                    // string to our text view.
                    responseTV.setText(responseString);

                    // Switch to MainActivity
                    switchToMainActivity(null);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(Register_screen.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Credentials> call, Throwable t) {
                // setting text to our text view when
                // we get an error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}