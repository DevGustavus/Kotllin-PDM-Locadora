package com.example.xml_film_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xml_film_project.databinding.ActivityInserirBinding

class InserirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInserirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInserirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listView.layoutManager = LinearLayoutManager(this)

        //Verificação de elementos que vieram de outra tela.
        val intencao = intent
        val bundle = intencao.extras
        val mensagemDaTelaPrincipal = bundle?.getString("123")
        Log.i("Teste","Mostra: "+mensagemDaTelaPrincipal)

        //Carregamento dos dados do banco para mostrar no RecylerView.
        val banco_filmes = MyDataBaseHelper(applicationContext)
        val operacoesBanco = DAO_Filmes(banco_filmes)
        atualizarRecycler(operacoesBanco)

        binding.btInserirFilme.setOnClickListener {
            //Inserção do novo filme no banco de dados.
            operacoesBanco.inserirFilme(Filme(gerarID(),binding.txtInputTitulo.text.toString(),binding.numInputCusto.text.toString().toFloat()))
            atualizarRecycler(operacoesBanco)
            limparCampos(binding.txtInputTitulo,binding.numInputCusto)
        }

        binding.btAtualizarFilme.setOnClickListener {
            operacoesBanco.atualizarFilme(Filme(gerarID(),binding.txtInputTitulo.text.toString(),binding.numInputCusto.text.toString().toFloat()))
            atualizarRecycler(operacoesBanco)
            limparCampos(binding.txtInputTitulo,binding.numInputCusto)
        }

        binding.btExcluirFilme.setOnClickListener {
            val listaFilmes = operacoesBanco.mostrarFilmes()

            if (listaFilmes.isNotEmpty()) {
                val primeiroFilme = listaFilmes[0]
                operacoesBanco.excluirFilme(primeiroFilme)
                atualizarRecycler(operacoesBanco)
                limparCampos(binding.txtInputTitulo, binding.numInputCusto)
            } else {
                Log.i("Teste", "Lista de filmes está vazia.")
            }
        }
    }

    companion object {
        private var ultimoIdGerado = 0

        fun gerarID(): Int {
            ultimoIdGerado++
            return ultimoIdGerado
        }
    }

    fun atualizarRecycler(operacoesBanco: DAO_Filmes ){
        //Busca dos elementos do banco de dados.
        val listaDeFilmes = operacoesBanco.mostrarFilmes()
        for(filme in listaDeFilmes){
            Log.i("Teste","-> "+filme)
        }
        //Inserção dos elementos do banco de dados no RecyclerView.
        var filmeAdaptador = FilmeAdapter(listaDeFilmes,applicationContext)
        binding.listView.adapter = filmeAdaptador
    }

    fun limparCampos(et_top_id : EditText, et_top_titulo : EditText){
        et_top_id.text.clear()
        et_top_titulo.text.clear()
    }
}
