package com.company.admindashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public MainAdapter(FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.PetName.setText(model.getPetName());
        holder.PetBreed.setText(model.getPetBreed());
        Picasso.get().load(model.getImageUrl()).into(holder.ImageUrl);

        holder.itemView.setOnClickListener(view -> {
            final   String view_pet_id = getRef(position).getKey();
            Intent intent = new Intent(holder.PetCard.getContext(), ManagePets.class);
            intent.putExtra("view_pet_id",view_pet_id);
            holder.PetCard.getContext().startActivity(intent);

        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_card_view, parent, false);


        return new myViewHolder(view);

    }


    class  myViewHolder extends RecyclerView.ViewHolder {
       ImageView ImageUrl;
        TextView PetName,PetBreed;
        CardView PetCard;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageUrl=itemView.findViewById(R.id.ivShowPet);
            PetName =itemView.findViewById(R.id.tvPetName);
            PetBreed=itemView.findViewById(R.id.tvPetBreed);
            PetCard=itemView.findViewById(R.id.petview);
        }
    }
}
