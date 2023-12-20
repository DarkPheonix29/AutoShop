package com.example.autoshop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.log_in);
        registerTextView = findViewById(R.id.Register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get email and password from EditText fields
                String enteredEmail = emailEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Perform login authentication
                authenticateUser(enteredEmail, enteredPassword);
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToRegisterScreen();
            }
        });
    }

    private void authenticateUser(String email, String password) {
        // Create an instance of the Retrofit API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.160.50:8080/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last, we are building our retrofit builder.
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // Call the authentication method on the API
        Call<CredentialsResult> loginCall = retrofitAPI.getCredentialsData(email, password);


        // Enqueue the call to perform the asynchronous network request
        loginCall.enqueue(new Callback<CredentialsResult>() {
            @Override
            public void onResponse(Call<CredentialsResult> call, Response<CredentialsResult> response) {
                if (response.isSuccessful() && response.body().success == true) {
                    switchToProductScreen();


                } else {
                    // Authentication failed, show an error message
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CredentialsResult> call, Throwable t) {
                // Network request failed, show an error message
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void switchToProductScreen() {
        Intent intent = new Intent(this, ProductScreen.class);
        startActivity(intent);
    }

    private void switchToRegisterScreen() {
        Intent intent = new Intent(this, Register_screen.class);
        startActivity(intent);
    }
}
