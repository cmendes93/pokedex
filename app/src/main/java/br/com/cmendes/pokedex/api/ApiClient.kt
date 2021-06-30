package br.com.cmendes.pokedex.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient<T> {
    val BASE_URL = "http://pokeapi.co/"

    fun getClient(c: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(c)
    }
}

fun getPokemonService(): PokemonService {
    return ApiClient<PokemonService>().getClient(PokemonService::class.java)
}