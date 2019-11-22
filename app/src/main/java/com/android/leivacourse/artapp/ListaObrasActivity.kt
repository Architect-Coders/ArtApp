package com.android.leivacourse.artapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.leivacourse.artapp.data.ListadoObrasRepository
import com.android.leivacourse.artapp.utils.replaceFragmentInActivity
import com.example.android.architecture.blueprints.todoapp.Injection

class ListaObrasActivity : AppCompatActivity() {

    private lateinit var listaobrasFragment : ListaObrasFragment
    private lateinit var listaObrasPresenter : ListaObrasPresenter
    private val CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaobrasFragment = supportFragmentManager
            .findFragmentById(R.id.content) as ListaObrasFragment?
            ?: ListaObrasFragment.newInstance().apply {
                arguments = Bundle().apply {
                }
            }.also { replaceFragmentInActivity(it, R.id.content) }


        // Create the presenter
        listaObrasPresenter = ListaObrasPresenter(
            Injection.provideTasksRepository(applicationContext),
            listaobrasFragment)

    }
}
