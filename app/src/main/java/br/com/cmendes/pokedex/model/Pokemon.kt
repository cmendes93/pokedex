package br.com.cmendes.pokedex.model

import com.google.gson.annotations.SerializedName

class Pokemon (

    @SerializedName("id") val id: String = "",
    @SerializedName("name") val nome: String = "",
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("types") var types: ArrayList<Types> = ArrayList()


)
