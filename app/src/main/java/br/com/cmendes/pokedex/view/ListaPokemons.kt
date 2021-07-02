package br.com.cmendes.pokedex.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cmendes.pokedex.R
import br.com.cmendes.pokedex.adapter.ListaPokemonAdapter
import br.com.cmendes.pokedex.api.getPokemonService
import br.com.cmendes.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_lista_pokemons.*

class ListaPokemons  : AppCompatActivity(){


    var pokemonList: ArrayList<Pokemon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pokemons)

        addData()
        exibeNaLista(pokemonList)
    }

    private fun addData() {
        val apiCliente = getPokemonService()
        for (i in 0..151) {
            apiCliente.getPokemon(i)
                .enqueue(object : Callback<Pokemon> {
                    override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
                        exibeMensagemErro(t)
                    }

                    override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>?) {
                        if(response!!.isSuccessful) {
                            //exibeNaLista(response?.body()!!.content)
                            val pokemon = response.body()
                            if (pokemon != null) {
                                pokemonList.add(pokemon)
                                exibeNaLista(pokemonList)
                            }

                        }
                    }
                })
        }
    }
    fun exibeMensagemErro(t: Throwable?) {
        Log.d("ERRORRR",t?.message.toString())
        Toast.makeText(this, t?.message, Toast.LENGTH_LONG).show()
    }

    fun exibeNaLista(pokemons: List<Pokemon>) {
        rv_pokemons.adapter = ListaPokemonAdapter(pokemons,
            this, {
            })
        rv_pokemons.layoutManager = LinearLayoutManager(this)
    }
}