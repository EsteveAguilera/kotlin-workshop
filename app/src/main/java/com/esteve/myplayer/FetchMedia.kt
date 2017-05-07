package com.esteve.myplayer

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

private val BASE_URL = "http://lorempixel.com/400/400/"

/*fun fetchMedia(): List<MediaItem> = listOf(
        MediaItem("Title 1", BASE_URL + 1, MediaItem.Type.PHOTO),
        MediaItem("Title 2", BASE_URL + 2, MediaItem.Type.PHOTO),
        MediaItem("Title 3", BASE_URL + 3, MediaItem.Type.VIDEO),
        MediaItem("Title 4", BASE_URL + 4, MediaItem.Type.PHOTO),
        MediaItem("Title 5", BASE_URL + 5, MediaItem.Type.PHOTO),
        MediaItem("Title 6", BASE_URL + 6, MediaItem.Type.VIDEO),
        MediaItem("Title 7", BASE_URL + 7, MediaItem.Type.PHOTO),
        MediaItem("Title 8", BASE_URL + 8, MediaItem.Type.PHOTO))*/

object MediaProvider {
    fun fetchMedia(category: String): List<MediaItem> = (1..10).map {
        MediaItem(it, "Title $it", "$BASE_URL/$category/$it", if(it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO)
    }

    fun fetchMediaAsync(callback: (List<MediaItem>) -> Unit) {
        doAsync {
            val items = fetchMedia("cats")
            uiThread {
                callback(items)
            }
        }
    }
}