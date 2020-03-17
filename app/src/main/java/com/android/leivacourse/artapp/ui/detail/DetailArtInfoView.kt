package com.android.leivacourse.artapp.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView as TextView
import androidx.core.text.bold
import com.android.leivacourse.artapp.data.local.model.ArtDetail

class DetailArtInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    fun setArt(art: ArtDetail) = with(art) {
        text = androidx.core.text.buildSpannedString {

            bold { append("Descripción: ") }
            appendln(description?:"N/A")

            bold { append("Autor: ") }
            appendln(user?.name?:"N/A")

            bold { append("Localización: ") }
            appendln(user?.location?:"N/A")

            val mPrice= String.format("%.2f",getRamdomPrice)

            bold { append("Precio: ") }
            appendln("$$mPrice MXN")
        }
    }
}