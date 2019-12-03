package com.android.leivacourse.artapp.ui.artgallery

import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.android.leivacourse.artapp.R
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
        return ArtWorksViewHolder(parent.inflate(R.layout.item_artwork))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ArtWorksViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

}

class ArtWorksViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ImageDetail, listener: (Any) -> Unit) {
        with(view) {
            listener(item)
            item_image.loadImage(item.url.small)
            item_username.text = item.user.username
        }
    }

}