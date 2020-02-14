package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.yoga.R;
import com.example.yoga.model.User;

public class RegisterActivity extends AppCompatActivity {

    EditText emailField;
    EditText passwordField;
    EditText nameField;
    EditText cityField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        nameField = (EditText) findViewById(R.id.nameField);
        cityField = (EditText) findViewById(R.id.cityField);
    }

    public void onUserRegster(View view) {
        Intent resultIntent = new Intent();

        User user = new User(emailField.getText().toString(),
                passwordField.getText().toString(),
                nameField.getText().toString(),
                cityField.getText().toString());

        resultIntent.putExtra("user", user);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
