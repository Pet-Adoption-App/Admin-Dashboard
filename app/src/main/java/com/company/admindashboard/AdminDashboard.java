package com.company.admindashboard;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class AdminDashboard extends AppCompatActivity {

    private Button btnPets, btnLostPets, btnLogout;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboard.this);
        builder.setTitle("Log out");
        builder.setMessage("Do you want to Log out?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(AdminDashboard.this,LoginActivity.class));
                AdminDashboard.this.finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        getWindow().setStatusBarColor(ContextCompat.getColor(AdminDashboard.this,R.color.black));

        btnPets = findViewById(R.id.btnPets);
        btnLostPets = findViewById(R.id.btnLostPets);
        btnLogout = findViewById(R.id.btnLogOut);



        btnLogout.setOnClickListener(view -> {
            startActivity(new Intent(this,LoginActivity.class));
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        });

        btnPets.setOnClickListener(view -> {
            startActivity(new Intent(this,PetList.class));
        });

        btnLostPets.setOnClickListener(view -> {
            startActivity(new Intent(this,LostPetList.class));
        });
    }
}