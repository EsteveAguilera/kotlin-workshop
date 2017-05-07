package com.esteve.myplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun Context.toast(s: String): Unit {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(id: Int): View {
    return LayoutInflater.from(context).inflate(id, this, false)
}

fun ImageView.loadUrl(url: String) {
    Picasso.with(context).load(url).into(this)
}

//inline fun <reified T : Activity> Context.startActivity() {
//    startActivity(Intent(this, T::class.java))
//}


inline fun <reified T : View> Activity.find(id: Int): T {
    return findViewById(id) as T
}

inline fun <reified T : View> RecyclerView.ViewHolder.find(id: Int): T {
    return itemView.findViewById(id) as T
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

