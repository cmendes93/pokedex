package br.com.cmendes.pokedex.model

import com.google.gson.annotations.SerializedName

data class Type(

    @SerializedName("name") val name: String = ""
)