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

    //private lateinit var  recyclerView: RecyclerView
    //private lateinit var pokemonAdapter :PokemonAdapter
    var pokemonList: ArrayList<Pokemon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pokemons)

        /*recyclerView = findViewById<View>(R.id.rv_pokemons) as RecyclerView

        pokemonAdapter = PokemonAdapter(pokemonList)

        val layoutManager: RecyclerView.LayoutManager
        layoutManager = LinearLayoutManager(applicationContext)

        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setAdapter(pokemonAdapter)*/

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
                            Log.d("POKEMON","posicao: " +pokemon?.id +" "+pokemon?.nome.toString() + " - " + pokemon?.sprites?.front_default)
                        }
                    }
                })
        }


        /*val apiService: PokemonService = client!!.create(PokemonService::class.java)
        for (i in 1..30) {
            val call: Call<Pokemon> = apiService.getPokemon(i)
            call.enqueue(object : Callback<Pokemon?> {
                override fun onResponse(
                    call: Call<Pokemon?>?,
                    response: Response<Pokemon?>
                ) {
                    if (response.isSuccessful()) {
                        val pokemon: Pokemon? = response.body()
                        if (pokemon != null) {
                            pokemonList.add(pokemon)
                        }
                        pokemonAdapter.notifyDataSetChanged()
                        if (pokemon != null) {
                            Log.i("POKEMON", "Name: " + pokemon.nome)
                        }
                        if (pokemon != null) {
                            Log.i("POKEMON", "Attack: " + pokemon.id)
                        }
                    }
                }

                override fun onFailure(call: Call<Pokemon?>?, t: Throwable?) {}
            })
        }*/
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