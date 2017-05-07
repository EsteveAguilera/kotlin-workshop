package com.esteve.myplayer

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlin.properties.Delegates

class MediaAdapter(val listener: (MediaItem) -> Unit) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items: List<MediaItem> by Delegates.observable(emptyList()) { p, old, new ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = parent.inflate(R.layout.view_media_item)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { listener(items[position]) }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: MediaItem) {
            with(itemView) {
                media_title.text = item.title
                media_thumb.loadUrl(item.url)
                media_video_indicator.visible = item.type == MediaItem.Type.VIDEO
            }
        }
    }
}


/**
 * some things
 */
fun testLambdas() {

    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val mul: (Int, Int) -> Int = { x: Int, y: Int -> x * y }

    applyF(sum, 3, 4)
    applyF(mul, 3, 4)
}

fun applyF(f: (Int, Int) -> Int, i1: Int, i2: Int) {
    f.invoke(i1, i2) // = f(i1, i2)
}