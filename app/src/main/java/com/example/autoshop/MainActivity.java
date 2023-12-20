package com.example.autoshop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assuming you have buttons with IDs button1 and button2
        Button button1 = findViewById(R.id.log_in);
        Button button2 = findViewById(R.id.Register);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToFirstActivity();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToSecondActivity();
            }
        });
    }

    private void switchToFirstActivity() {
        Intent intent = new Intent(this, ProductScreen.class); // Replace FirstActivity.class with the actual class for the first activity
        startActivity(intent);
    }

    private void switchToSecondActivity() {
        Intent intent = new Intent(this, Register_screen.class); // Replace SecondActivity.class with the actual class for the second activity
        startActivity(intent);
    }
}
