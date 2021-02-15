package com.example.filmsapplication.util.extansions

import android.content.Intent
import androidx.fragment.app.Fragment


fun Fragment.shareFilm(itemId: Long) {
    val shareUrl = "https://www.themoviedb.org/movie/${itemId}"
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareUrl)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    this.startActivity(shareIntent)
}