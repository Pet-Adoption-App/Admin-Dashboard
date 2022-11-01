package com.company.admindashboard.LostPet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.admindashboard.Model.MainModel;
import com.company.admindashboard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ManageLostPets extends AppCompatActivity {

    String receivePetID, NgoID;
    DatabaseReference LostpetReference, approvedLostPetRef, NgoRef;
    ImageView ivViewPetsNGO;
    TextView tvAboutViewPetsNGO,tvPetNameViewPetsNGO,tvPetAgeViewPetsNGO,tvPetBreedViewPetsNGO,tvPetGenderViewPetsNGO, tvPetAddressNGO;
    Button btnApproveManagePetsNGO,btnDeleteManagePetsNGO;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ManageLostPets.this, LostPetList.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_lost_pets);

        tvAboutViewPetsNGO=findViewById(R.id.tvAboutViewPetsNGO);
        tvPetAgeViewPetsNGO=findViewById(R.id.tvPetAgeViewPetsNGO);
        tvPetBreedViewPetsNGO=findViewById(R.id.tvPetBreedViewPetsNGO);
        tvPetGenderViewPetsNGO=findViewById(R.id.tvPetGenderViewPetsNGO);
        tvPetNameViewPetsNGO=findViewById(R.id.tvPetNameViewPetsNGO);
        ivViewPetsNGO=findViewById(R.id.ivViewPetsNGO);
        tvPetAddressNGO= findViewById(R.id.tvPetAddressViewPetsNGO);

        btnApproveManagePetsNGO=findViewById(R.id.btnApproveManagePetsNGO);
        btnDeleteManagePetsNGO=findViewById(R.id.btnDeleteManagePetsNGO);


        approvedLostPetRef = FirebaseDatabase.getInstance().getReference().child("Lost_Approved_req");
        LostpetReference = FirebaseDatabase.getInstance().getReference().child("Lost_Approval_req");
        NgoRef = FirebaseDatabase.getInstance().getReference().child("NGO");
        receivePetID = getIntent().getStringExtra("view_lost_pet_id");
        //System.out.println(receivePetID);

        LostpetReference.child(receivePetID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MainModel pet = snapshot.getValue(MainModel.class);

                tvAboutViewPetsNGO.setText(pet.getPetAbout());
                tvPetAgeViewPetsNGO.setText(pet.getPetAge());
                tvPetBreedViewPetsNGO.setText(pet.getPetBreed());
                tvPetNameViewPetsNGO.setText(pet.getPetName());
                Picasso.get().load(pet.getImageUrl()).into(ivViewPetsNGO);
                tvPetGenderViewPetsNGO.setText(pet.getPetGender());
                tvPetAddressNGO.setText(pet.getPetAddress());
                NgoID = pet.getPetUser();
                System.out.println("%%%%%%%%%%%%%%%%%%%%%******>>"+NgoID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnApproveManagePetsNGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyRecord(LostpetReference.child(receivePetID),
                        approvedLostPetRef.child(receivePetID));
                startActivity(new Intent(ManageLostPets.this,LostPetList.class));
                finish();
            }
        });




        btnDeleteManagePetsNGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LostpetReference.child(receivePetID).removeValue();
                Toast.makeText(ManageLostPets.this, "Disapproved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ManageLostPets.this,LostPetList.class));
                finish();
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
                            addToNGOPets();
                        }
                    }
                });
                LostpetReference.child(receivePetID).removeValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "onCancelled- copy fail", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void addToNGOPets() {
        NgoRef.child(NgoID).child("NgoPets").child(receivePetID).child("PetID").setValue(receivePetID)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(ManageLostPets.this,LostPetList.class));
                        }else{
                            Toast.makeText(ManageLostPets.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
