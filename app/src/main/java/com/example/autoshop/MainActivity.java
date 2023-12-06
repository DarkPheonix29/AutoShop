package com.example.autoshop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // You can also set click listeners programmatically if needed
        Button loginButton = findViewById(R.id.log_in);
        Button registerButton = findViewById(R.id.Register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSecondActivity();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegisterScreen();
            }
        });
    }

    private void switchToSecondActivity() {
        Intent intent = new Intent(this, ProductScreen.class);
        startActivity(intent);
    }

    private void switchToRegisterScreen() {
        Intent intent = new Intent(this, Register_screen.class);
        startActivity(intent);
    }
}
