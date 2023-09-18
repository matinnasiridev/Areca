package me.nasiri.areca.data.model

import me.nasiri.areca.R

data class BoardData(
    val image: Int,
    val title: String,
    val des: String
) {
    companion object {
        fun get(): List<BoardData> {
            return listOf(
                BoardData(R.drawable.ic_launcher_foreground, "onepage", "DES"),
                BoardData(R.drawable.ic_launcher_foreground, "onepage", "DES"),
                BoardData(R.drawable.ic_launcher_foreground, "onepage", "DES")
            )
        }
    }
}
