package com.android.leivacourse.artapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.leivacourse.artapp.utils.replaceFragmentInActivity

class ListaObrasActivity : AppCompatActivity() {

    private lateinit var listaobrasFragment : ListaObrasFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaobrasFragment = supportFragmentManager
            .findFragmentById(R.id.content) as ListaObrasFragment?
            ?: ListaObrasFragment.newInstance().apply {
                arguments = Bundle().apply {
                }
            }.also { replaceFragmentInActivity(it, R.id.content) }





    }
}
