package com.company.admindashboard.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.admindashboard.Main.AdminDashboard;
import com.company.admindashboard.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etLoginPassword;
    private Button btnLogin;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.black));

        etUsername = findViewById(R.id.etUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            String username = etUsername.getText().toString();
            String password = etLoginPassword.getText().toString();

            if(username.equals("admin") && password.equals("1234")){
                startActivity(new Intent(this, AdminDashboard.class));
            }else{
                Toast.makeText(this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
            }
        });

    }
}