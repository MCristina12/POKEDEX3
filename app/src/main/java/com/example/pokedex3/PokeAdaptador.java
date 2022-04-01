package com.example.pokedex3;

import android.content.Context;
import android.content.Intent;
import  android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class PokeAdaptador extends RecyclerView.Adapter<PokeAdaptador.PokeViewHolder>{

    private ArrayList<Pokemon> pokemons;
    private Context context;

    public PokeAdaptador(Context context) {
        this.pokemons = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poke_cardview, parent, false);
        return new PokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokeViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.pokename.setText(pokemon.getName());




        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getNumber()+".png")
        .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.pokeimg);


        holder.pokeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, pokemon.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, PokeDetalle.class);
                intent.putExtra("PokeNombre", pokemon.getName());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void addPokeLista(ArrayList<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
        notifyDataSetChanged();
    }

    public class PokeViewHolder extends RecyclerView.ViewHolder{

        private ImageView pokeimg;
        private TextView pokename;

        public PokeViewHolder(@NonNull View itemView) {
            super(itemView);

            pokeimg = (ImageView) itemView.findViewById(R.id.cv_img);
            pokename = (TextView) itemView.findViewById(R.id.cv_pokename);
        }
    }
}
