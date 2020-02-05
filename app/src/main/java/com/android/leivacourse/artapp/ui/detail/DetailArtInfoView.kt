package com.android.leivacourse.artapp.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.text.bold
import com.android.leivacourse.artapp.data.local.model.ImageDetail

class DetailArtInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    fun setArt(art: ImageDetail) = with(art) {
        text = androidx.core.text.buildSpannedString {

            bold { append("Descripción: ") }
            appendln(description?:"N/A")

            bold { append("Autor: ") }
            appendln(user?.firstName?:"N/A")

            bold { append("Localización: ") }
            appendln(user?.location?:"N/A")

            val mPrice= String.format("%.2f",getRamdomPrice)

            bold { append("Precio: ") }
            appendln("$mPrice MXN")
        }
    }
}