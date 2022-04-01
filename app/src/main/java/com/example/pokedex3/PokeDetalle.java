package com.example.pokedex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedex3.pokeapi.PokeApi;
import com.example.pokedex3.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokeDetalle extends AppCompatActivity {

    private ImageView pokeImagen;
    private TextView pokeNombre;
    private TextView pokeBaseExperience;
    private TextView pokeHeight;
    private TextView pokeWeight;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poke_detalle);


        Bundle parametros = getIntent().getExtras();
        String pNombre = parametros.getString("PokeNombre");

        pokeImagen = (ImageView) findViewById(R.id.pokeImg_detail);
        pokeNombre = (TextView) findViewById(R.id.pokeNombre_detail);
        pokeBaseExperience = (TextView) findViewById(R.id.poke_BaseExperience);
        pokeHeight = (TextView) findViewById(R.id.poke_height);
        pokeWeight = (TextView) findViewById(R.id.poke_weight);

        pokeNombre.setText(pNombre);

    }
}