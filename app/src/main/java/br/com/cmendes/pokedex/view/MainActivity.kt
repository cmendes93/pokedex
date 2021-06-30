package br.com.cmendes.pokedex.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.cmendes.pokedex.R

class MainActivity : AppCompatActivity() {

    private lateinit var  btnSobre: Button
    private lateinit var  btnVerPokemons: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSobre = findViewById(R.id.btnSobre)
        btnVerPokemons = findViewById(R.id.btnVerPokemons)

        btnSobre.setOnClickListener {
            val sobreScreen = Intent(this, SobreActivity::class.java)
            startActivity(sobreScreen)
        }

        btnVerPokemons.setOnClickListener {
            val ListaPokemonsScreen = Intent(this, ListaPokemons::class.java)
            startActivity(ListaPokemonsScreen)
        }
    }
}