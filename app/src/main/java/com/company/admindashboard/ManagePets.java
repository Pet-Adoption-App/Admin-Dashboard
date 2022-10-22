package com.company.admindashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;



public class ManagePets extends AppCompatActivity {

    String receivePetID;
    DatabaseReference petReference;
    ImageView ivViewPets;
    TextView tvAboutViewPets, tvPetNameViewPets, tvPetAgeViewPets, tvPetBreedViewPets, tvPetGenderViewPets;
    Button btnApproveManagePets, btnDeleteManagePets;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ManagePets.this, PetList.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pets);

        tvAboutViewPets = findViewById(R.id.tvAboutViewPets);
        tvPetAgeViewPets = findViewById(R.id.tvPetAgeViewPets);
        tvPetBreedViewPets = findViewById(R.id.tvPetBreedViewPets);
        tvPetGenderViewPets = findViewById(R.id.tvPetGenderViewPets);
        tvPetNameViewPets = findViewById(R.id.tvPetNameViewPets);
        ivViewPets = findViewById(R.id.ivViewPets);
        btnApproveManagePets = findViewById(R.id.btnApproveManagePets);
        btnDeleteManagePets = findViewById(R.id.btnDeleteManagePets);

        petReference = FirebaseDatabase.getInstance().getReference().child("Approval_req");
        receivePetID = getIntent().getStringExtra("view_pet_id");


        petReference.child(receivePetID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MainModel pet = snapshot.getValue(MainModel.class);

                tvPetNameViewPets.setText(pet.getPetName());
                tvAboutViewPets.setText(pet.getPetAbout());
                tvPetAgeViewPets.setText(pet.getPetAge());
                tvPetBreedViewPets.setText(pet.getPetBreed());
                Picasso.get().load(pet.getImageUrl()).into(ivViewPets);
                tvPetGenderViewPets.setText(pet.getPetGender());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnApproveManagePets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyRecord(petReference.child(receivePetID),
                        FirebaseDatabase.getInstance().getReference().child("Approved_req").child(receivePetID));



            }
        });


        btnDeleteManagePets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petReference.child(receivePetID).removeValue();
                Toast.makeText(ManagePets.this, "Disapproved", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void copyRecord(DatabaseReference fromPath, final DatabaseReference toPath) {
        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Toast.makeText(getApplicationContext(), "Approval Failed", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Approved", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                petReference.child(receivePetID).removeValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "onCancelled- copy fail", Toast.LENGTH_LONG).show();

            }
        });
    }


}

