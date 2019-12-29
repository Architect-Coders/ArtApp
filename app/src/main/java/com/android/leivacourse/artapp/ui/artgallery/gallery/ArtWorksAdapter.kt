package com.android.leivacourse.artapp.ui.artgallery.gallery

import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.utils.inflate
import com.android.leivacourse.artapp.utils.loadImage
import kotlinx.android.synthetic.main.item_artwork.view.*
import kotlin.properties.Delegates

class ArtWorksAdapter(@NonNull private val listener: (ImageDetail) -> Unit) :
    RecyclerView.Adapter<ArtWorksViewHolder>() {

    var items by Delegates.observable(emptyList<ImageDetail>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtWorksViewHolder {
        return ArtWorksViewHolder(
            parent.inflate(
                R.layout.item_artwork
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ArtWorksViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

}

class ArtWorksViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ImageDetail, listener: (ImageDetail) -> Unit) {
        with(view) {
            item_image.loadImage(item.urls?.small?:"")
            item_username.text = item.user?.name?:"N/A"
            item_photouser.loadImage(item.user?.profileImage?.small?:"")
            view.setOnClickListener { listener(item) }
        }
    }

}