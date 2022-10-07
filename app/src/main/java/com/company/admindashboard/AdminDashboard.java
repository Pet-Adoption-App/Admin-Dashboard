package com.company.admindashboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminDashboard extends AppCompatActivity {

    private Button btnPets, btnLostPets, btnLostPetsNGO, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        getWindow().setStatusBarColor(ContextCompat.getColor(AdminDashboard.this,R.color.black));

        btnPets = findViewById(R.id.btnPets);
        btnLostPets = findViewById(R.id.btnLostPets);
        btnLostPetsNGO = findViewById(R.id.btnLostPetsNGO);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(view -> {
            startActivity(new Intent(this,LoginActivity.class));
        });

        btnPets.setOnClickListener(view -> {
            startActivity(new Intent(this,PetList.class));
        });

        btnLostPets.setOnClickListener(view -> {
            startActivity(new Intent(this,ManageLostPets.class));
        });
        
        btnLostPets.setOnClickListener(view -> {
            startActivity(new Intent(this,ManageLostPetsNGO.class));
        });
    }
}