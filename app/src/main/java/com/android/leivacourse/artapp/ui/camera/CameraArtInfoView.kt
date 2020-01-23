package com.android.leivacourse.artapp.ui.camera

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.text.bold
import com.android.leivacourse.artapp.data.local.model.ImageDetail

class CameraArtInfoView @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {


    fun setDetail(des: ImageDetail) = with(des){

        text = androidx.core.text.buildSpannedString {

            bold { append("Descripción: ") }
            appendln( description?:"N/A")

            bold { append("Autor: ") }
            appendln(user?.name?:"N/A")

            bold { append("Localización: ") }
            appendln(user?.location?:"N/A")
        }

    }
}