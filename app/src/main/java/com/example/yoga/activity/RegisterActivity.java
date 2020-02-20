package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoga.R;
import com.example.yoga.db.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextEmail;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    EditText mTextCity;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextEmail = (EditText) findViewById(R.id.edittext_email);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText) findViewById(R.id.edittext_cnf_password);
        mTextCity = (EditText) findViewById(R.id.edittext_city);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String email = mTextEmail.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();
                String city = mTextCity.getText().toString().trim();

                if (user.isEmpty() || email.isEmpty() || city.isEmpty() || pwd.isEmpty() || cnf_pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Пополнете ги сите полиња", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.equals(cnf_pwd)) {
                        boolean userExists = db.checkUser(user, pwd);
                        if (userExists) {
                            Toast.makeText(RegisterActivity.this, "Веќе сте регистрирани", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(moveToLogin);
                        } else {
                            long val = db.addUser(user, email, pwd, city);
                            if (val > 0) {
                                Toast.makeText(RegisterActivity.this, "Успешна регистрација", Toast.LENGTH_LONG).show();
                                Intent moveToLog = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(moveToLog);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Грешка при регистрација", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            }

        });
    }
}