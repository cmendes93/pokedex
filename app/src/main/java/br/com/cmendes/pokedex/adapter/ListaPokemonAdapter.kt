package br.com.cmendes.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.cmendes.pokedex.R
import br.com.cmendes.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_item.view.*

import com.squareup.picasso.Picasso


class ListaPokemonAdapter(private val pokemons: List<Pokemon>,
                          private val context: Context,
                          val listener: (Pokemon) -> Unit) :
        RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder>() {


    private lateinit var  tvNomePokemon: TextView

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder?.let {
            it.bindView(pokemon, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemon: Pokemon,
                     listener: (Pokemon) -> Unit) = with(itemView) {

            tvNomePokemon.text = pokemon.nome
            tvNumero.text = pokemon.id

            var tipoConcatenado : String = ""
            for (tipo in pokemon.types){
                tipoConcatenado += tipo.type.name + " "
            }

            tvTipo.text = tipoConcatenado

            Picasso.get()
                .load(pokemon?.sprites?.front_default)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.drawable.pikachu)
                .into(ivPokemon);

            setOnClickListener { listener(pokemon) }
        }
    }
}