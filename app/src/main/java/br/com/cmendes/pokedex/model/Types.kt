package br.com.cmendes.pokedex.model

import com.google.gson.annotations.SerializedName

data class Types(

        @SerializedName("type") var type: Type


) {
    override fun toString(): String {
        return "$type"
    }
}