package com.android.leivacourse.artapp.ui.artgallery.fav

import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.utils.inflate
import com.android.leivacourse.artapp.utils.loadImage
import kotlinx.android.synthetic.main.item_favorite.view.*
import kotlin.properties.Delegates

class FavoriteItemAdapter(@NonNull private val listener: (ImageDetail) -> Unit): RecyclerView.Adapter<FavoriteItemHolder>(){

    var items by Delegates.observable(emptyList<ImageDetail>()) { _, _, _ ->
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FavoriteItemHolder {
        return FavoriteItemHolder(
            parent.inflate(
                R.layout.item_favorite
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: FavoriteItemHolder, position: Int) {

        holder.bind(items[position],listener)
    }

}

class FavoriteItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ImageDetail, listener: (ImageDetail) -> Unit) {
        with(view) {
            item_favorite_image.loadImage(item.urls?.small?:"")
            item_favorite_username.text = item.user?.name?:"N/A"
            item_favorite_photouser.loadImage(item.user?.profileImage?.small?:"")
            /**
             * Ejecutar logica para quitar de la base de datos
             */
            btn_favorite.setOnClickListener{listener(item)}
        }
    }

}