package com.android.leivacourse.artapp.data

import com.android.leivacourse.artapp.data.model.Obra

interface ListadoObrasRepository {

    fun obtenerObras() : MutableList<Obra>

    companion object{}
}