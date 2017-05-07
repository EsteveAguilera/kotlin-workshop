package com.esteve.myplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ID = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(EXTRA_ID, -1)

        MediaProvider.fetchMediaAsync { items ->
            val item = items.firstOrNull { it.id == id }
            item?.let {
                detail_thumb.loadUrl(it.url)
                detail_video_indicator.visible = it.type == MediaItem.Type.VIDEO
                supportActionBar?.title = it.title
            }
        }
    }
}