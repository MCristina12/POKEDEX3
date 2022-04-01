package com.example.pokedex3;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pokedex3.pokeapi.PokeApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private PokeAdaptador pokeAdaptador;

    private int offset;

    private boolean aptoCarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rvPokemon);
        pokeAdaptador = new PokeAdaptador(this);
        recyclerView.setAdapter(pokeAdaptador);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(glm);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                    int visibleItemCount = glm.getChildCount();
                    int totalItemCount = glm.getItemCount();
                    int pastVisibleItem = glm.findFirstVisibleItemPosition();

                    if(aptoCarga){
                        if((visibleItemCount+pastVisibleItem)>=totalItemCount){
                            Log.i(TAG, "Llegamos al fin");
                            aptoCarga = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();

        aptoCarga = true;
        offset = 0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset) {
        PokeApi service = retrofit.create(PokeApi.class);
        Call<PokeRespuesta> pokeRespuestaCall = service.getPokeLista(20, offset);

        pokeRespuestaCall.enqueue(new Callback<PokeRespuesta>() {
            @Override
            public void onResponse(Call<PokeRespuesta> call, Response<PokeRespuesta> response) {
                //llega
                aptoCarga = true;
                if(response.isSuccessful()){
                    PokeRespuesta pokeRespuesta = response.body();
                    ArrayList<Pokemon> pokemons = pokeRespuesta.getResults();
                    pokeAdaptador.addPokeLista(pokemons);


                }else{
                    Log.e(TAG, " onResponse: + "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokeRespuesta> call, Throwable t) {
                //no llega
                aptoCarga = true;
                Log.e(TAG, " onFailure: + "+t.getMessage());
            }
        });
    }

    public void ObtenerDatosDetalles(){
        PokeApi service = retrofit.create(PokeApi.class);
        Call<PokeDetalle> pokeDetalleCall = service.getPokeDetalle("1");

    }
}