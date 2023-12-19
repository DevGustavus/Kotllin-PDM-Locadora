package com.example.xml_film_project

import android.content.ContentValues
import android.util.Log

class DAO_Filmes(banco : MyDataBaseHelper)
{
    var banco : MyDataBaseHelper
    init {
        this.banco = banco
    }
    fun inserirFilme(filme : Filme){
        val db_insercao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("id", filme.id)
            put("titulo", filme.titulo)
            put("custo", filme.custo)
        }
        val confirmaInsercao = db_insercao?.insert("Filme",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
    }
    fun mostrarFilmes(): List<Filme>{
        val listaFilmes = ArrayList<Filme>()
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Filme",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val titulo = getString(getColumnIndexOrThrow("titulo"))
                val custo = getFloat(getColumnIndexOrThrow("custo"))
                Log.i("Teste","ID: "+id+" - Título: "+titulo+" - Custo: "+custo)
                listaFilmes.add(Filme(id,titulo,custo))
            }
        }
        cursor.close()
        return(listaFilmes)
    }
    fun atualizarFilme(filme : Filme){
        val db_atualizacao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("titulo", filme.titulo)
            put("custo", filme.custo)
        }
        val condicao = "id = "+filme.id
        val confirmaAtualizacao = db_atualizacao.update("Filme", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
    }
    fun excluirFilme(filme : Filme){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "id = "+filme.id
        val confirmaExclusao = db_exclusao.delete("Filme", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }

}
