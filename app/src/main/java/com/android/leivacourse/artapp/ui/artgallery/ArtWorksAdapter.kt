package com.android.leivacourse.artapp.ui.artgallery

import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.utils.inflate
import kotlin.properties.Delegates

class ArtWorksAdapter(@NonNull private val listener: (Any) -> Unit) :
    RecyclerView.Adapter<ArtWorksViewHolder>() {

    var items by Delegates.observable(emptyList<Any>()) { _, _, _ ->
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

    fun bind(item: Any, listener: (Any) -> Unit) {
        with(view) {
            listener(item)

        }
    }

}