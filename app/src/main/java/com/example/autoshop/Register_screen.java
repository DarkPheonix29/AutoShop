package com.example.autoshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Register_screen extends AppCompatActivity {

    private CheckBox agreeCheckBox;
    private TextView agreeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        agreeCheckBox = findViewById(R.id.checkBox);
        agreeMessage = findViewById(R.id.agree_message);
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
}
