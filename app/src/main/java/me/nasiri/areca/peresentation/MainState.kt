package me.nasiri.areca.peresentation

import me.nasiri.areca.data.model.BoardData

data class MainState(
    val boardListData: List<BoardData> = BoardData.get()
)
