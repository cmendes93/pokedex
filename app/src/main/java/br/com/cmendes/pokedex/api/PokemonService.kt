package br.com.cmendes.pokedex.api

import br.com.cmendes.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonService {

    @GET("api/v2/pokemon/{id}")
    fun getPokemon(@Path("id") id: Int):
            Call<Pokemon>
}