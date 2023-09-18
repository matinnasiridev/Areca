package me.nasiri.areca.peresentation.main

import me.nasiri.areca.data.model.BoardData

data class MainState(
    val boardListData: List<BoardData> = BoardData.get()
)
