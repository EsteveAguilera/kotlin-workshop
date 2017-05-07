package com.esteve.myplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import co.metalab.asyncawait.async
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private val adapter = MediaAdapter { navigateTo(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter
        updateData(Filter.None)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_photos -> Filter.ByType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(MediaItem.Type.VIDEO)
            else -> Filter.None
        }

        updateData(filter)
        return true
    }

    fun updateData(filter: Filter) {

        async {
            val cats = await { MediaProvider.fetchMedia("cats") }
            val nature = await { MediaProvider.fetchMedia("nature")}

            val items = cats + nature

            adapter.items = when(filter) {
                is Filter.None -> items
                is Filter.ByType -> items.filter { it.type == filter.type }
            }
        }
    }

    private fun navigateTo(item: MediaItem) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to item.id)
    }
}