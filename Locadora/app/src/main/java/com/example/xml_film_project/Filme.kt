package com.example.xml_film_project

class Filme(id: Int, titulo: String, custo: Float)
{
    var id : Int
    var titulo : String
    var custo : Float

    init {
        this.id = id
        this.titulo = titulo
        this.custo = custo
    }

    override fun toString(): String {
        return "Filme(id=$id, titulo='$titulo', custo=$custo)"
    }

}
