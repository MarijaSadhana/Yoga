package com.example.yoga.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yoga.R;
import com.example.yoga.model.User;

public class LoginActivity extends AppCompatActivity {

    final int REGISTER_REQUEST = 1000;

    String emailValue;
    String passwordValue;

    EditText emailField;
    EditText passwordField;
    SharedPreferences sharedPreferences;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        sharedPreferences = getSharedPreferences("MY_SHARED_PREF",MODE_PRIVATE);

        emailValue = sharedPreferences.getString("email",null);
        passwordValue = sharedPreferences.getString("password",null);

    }

    public void onLoginClick(View view) {

        if(emailValue == null || passwordValue == null){
            Toast.makeText(this,
                    "Регистрирај се!",
                    Toast.LENGTH_LONG).show();
        } else if(emailField.getText().toString().equals("") || passwordField.getText().toString().equals("")){
            Toast.makeText(this,
                    "Пополни ги полињата за меил и лозинка!",
                    Toast.LENGTH_LONG).show();
        } else {
            if(emailValue.equals(emailField.getText().toString()) && passwordValue.equals(passwordField.getText().toString())){
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                loginIntent.putExtra("user", user);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email",user.getEmail());
                editor.putString("password",user.getPassword());
                editor.putString("name",user.getName());
                editor.putString("city",user.getCity());
                editor.putBoolean("isLogedIn",true);
                editor.commit();

                startActivity(loginIntent);

            } else {
                Toast.makeText(this,
                        "Погрешно внесен меил и лозинка!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onRegisterClick(View view) {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(registerIntent, REGISTER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == REGISTER_REQUEST){

                if(data.hasExtra("user")){
                    user = (User) data.getParcelableExtra("user");
                    Log.d("USER NAME", user.getName());
                    emailValue = user.getEmail();
                    passwordValue = user.getPassword();
                }
            }
        }
    }

    public void onSkipClick(View view) {
        Intent skipIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(skipIntent);
    }
}
