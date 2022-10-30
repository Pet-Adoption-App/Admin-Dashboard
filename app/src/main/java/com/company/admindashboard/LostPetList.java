package com.company.admindashboard;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class LostPetList extends AppCompatActivity {
    RecyclerView recyclerView;
    LostPetAdapter lostPetAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_pet_list);


        recyclerView=(RecyclerView)findViewById(R.id.rv_lost_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Lost_Approval_req"), MainModel.class)
                        .build();

        lostPetAdapter= new LostPetAdapter(options);
        recyclerView.setAdapter(lostPetAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lostPetAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        lostPetAdapter.stopListening();
    }
}
