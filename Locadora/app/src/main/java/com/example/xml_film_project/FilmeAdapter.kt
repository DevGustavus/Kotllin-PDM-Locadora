package com.example.xml_film_project

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FilmeAdapter(private val listaFilmes: List<Filme>, val contexto : Context) : RecyclerView.Adapter<FilmeAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val id: TextView = itemView.findViewById(R.id.tv_item_id)
        val titulo: TextView = itemView.findViewById(R.id.tv_item_titulo)
        val custo: TextView = itemView.findViewById(R.id.tv_item_custo)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista_filmes, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewholder: ViewHolder, position: Int)
    {
        val filme = listaFilmes[position]

        viewholder.id.text = filme.id.toString()
        viewholder.titulo.text = filme.titulo
        viewholder.custo.text = filme.custo.toString()

        viewholder.itemView.setOnClickListener {
            Log.i("Teste", "Clicado: $filme - posição: $position")
            Toast.makeText(contexto, " [$position]: $filme", Toast.LENGTH_LONG).show()
        }
    }
    override fun getItemCount(): Int
    {
        return listaFilmes.size
    }
}
