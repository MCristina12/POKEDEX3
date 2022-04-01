package com.example.pokedex3.pokeapi;

import com.example.pokedex3.PokeDetalle;
import com.example.pokedex3.PokeRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApi {

    @GET("pokemon")
    Call<PokeRespuesta> getPokeLista(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<PokeDetalle> getPokeDetalle(@Path("id") String id);
}
